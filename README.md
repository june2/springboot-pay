## Pay app java server api

### This app include the following features:

- Java8
- Spring
- Spring boot
- Gradle
- Jpa
- hibernate
- querydsl
- swagger
- mysql

## Commands

### Build

```zsh
$ ./gradlew clean build
```

### Run

```zsh
$ ./gradlew bootRun
# Or run it as an executable jar:
$ java -jar build/libs/pay-app-0.1.0.jar
```

### Swagger
```
http://localhost:3001/swagger-ui.html
```

---
### ERD 설계
![image](https://user-images.githubusercontent.com/5827617/85914096-3b12e580-b875-11ea-8953-f0ad162e1370.png)
- 사용자 잔액 관련 테이블은 제외, 사용자 필드에서 처리
- room_user 방에 참여한 사용자 리스트
- distributing 방에 뿌리기 정보
  - token, user_id, room_id 복합키로 중복 제거 필요
  - 또는 유효시간으로 중복 제거 필요
- distributing_user 뿌리기 받은 유저 정보
---

### API 설계
- 뿌리기 `POST /api/users/room/distributing`
  - req 
      - header : X-USER-ID, X-ROOM-ID 
      - body : {
                 "amount": 10,(뿌릴 금액) 
                 "number": 2(뿌릴 인원)
               }
  - res
      - data: token               

- 받기 `GET /api/users/room/distributing`
    - req 
        - header : X-USER-ID, X-ROOM-ID
        - query : token
    - res
        - data: {amount: 받은금액, takenAt: 받은시각, userId: 받은 아이디} 
  
- 조회 `GET /api/users/room/distributing/users`
    - req 
        - header : X-USER-ID, X-ROOM-ID
         - query : token
    - res
        - data: {date: 뿌린시각, amount: 뿌린금액, amount: 받기완료된 금액, users: [받기완료된 정보]}

---

### 상세 구현 체크리스트
1. 뿌리기 API
 - [x] 뿌릴 금액, 뿌릴 인원을 요청값으로 받습니다.
 - [x] 뿌리기 요청건에 대한 고유 token을 발급하고 응답값으로 내려줍니다.
  - [x] 뿌릴 금액을 인원수에 맞게 분배하여 저장합니다. 
 - [x] token은 3자리 문자열로 구성되며 예측이 불가능해야 합니다.
2. 받기 API
 - [x] 뿌리기 시 발급된 token을 요청값으로 받습니다.
 - [x] token에 해당하는 뿌리기 건 중 아직 누구에게도 할당되지 않은 분배건 하나를 API를 호출한 사용자에게 할당하고, 그 금액을 응답값으로 내려줍니다.
 - [x] 뿌리기 당 한 사용자는 한번만 받을 수 있습니다.
 - [x] 자신이 뿌리기한 건은 자신이 받을 수 없습니다.
 - [x] 뿌린기가 호출된 대화방과 동일한 대화방에 속한 사용자만이 받을 수 있습니다.
 - [x] 뿌린 건은 10분간만 유효합니다. 뿌린지 10분이 지난 요청에 대해서는 받기 실패 응답이 내려가야 합니다.
3. 조회 API
 - [x] 뿌리기 시 발급된 token을 요청값으로 받습니다.
 - [x] token에 해당하는 뿌리기 건의 현재 상태를 응답값으로 내려줍니다. 현재 상태는 다음의 정보를 포함합니다.
 - [x] 뿌린 시각, 뿌린 금액, 받기 완료된 금액, 받기 완료된 정보 ([받은 금액, 받은 사용자 아이디] 리스트)
 - [x] 뿌린 사람 자신만 조회를 할 수 있습니다. 다른사람의 뿌리기건이나 유효하지 않은 token에 대해서는 조회 실패 응답이 내려가야 합니다.
 - [x] 뿌린 건에 대한 조회는 7일 동안 할 수 있습니다.
 
---

### API 에러 코드 설계
- SUCCESS : C200 /  "Success"
- ERROR : C500 /  "System error"
- BAD_REQUEST : C400 /  "파라미터 누락, 잘못 요청입니다."
- FORBIDEEN : C403 /  "잘못된 요청입니다."
- NOT_FOUND : C404 /  "Not found"
- DUPLICATED : C409 /  "Duplicated data"
- UNAUTHORIZED : C401 /  "Unauthorized"
- INVALID_TOKEN : C4010 /  "유효한 토큰이 아닙니다"
- INVALID_USER : C4011 /  "유효한 사용자가 아닙니다"
- INVALID_TIME : C4012 /  "토큰시간이 만료되었습니다."
- ALREADY_TAKEN : C4013 /  "이미 획득했습니다."
- NOT_ENOUGH : C4014 /  "잔액이 부족합니다."
- EVERY_TAKEN : C4015 / "이미 모두 소진되었습니다."

---

### 설계 가설 설정
- 뿌리기 금액은 뿌리기 인원에 정확히 나누어 떨어져야한다. 아니면 에러발생
- 방 인원수에 상관없이 뿌리기 인원을 설정할수 있다. 
  - 뿌리기 수가 방 인원수가 초과 가능  
  
---

### 테스트 코드 작성
- unit & until test
  - 시간 체크 테스트
  - 토큰 생성 테스트
  - 리스트 sum 테스트
  - repo & service 테스트
- API test
  - 뿌리기 API 테스트 
     - 유효한 방이 아닐때
     - 잘못된 body 요청 (뿌리기 인원 / 뿌리기 금액이 0 일때)
     - 사용자 잔액부족
     - 정상 동작, 토큰리턴 길이 3, 잔액 차감 확인
  - 받기 API 테스트
     - 유효한 방이 아닐때
     - 유효한 토큰이 아닐때
     - 뿌린 본인이 요청할때, 금지
     - 토큰 타임아웃 체크
     - 이미 가져간 유저, 에러
     - 뿌리기 소진 체크 / 설정인원초과 에러
     - 정상동작, 가져간 금액 정보 응답, 잔액 증감 확인     
  - 조회 API 테스트
     - 유효한 방이 아닐때
     - 유효한 토큰이 아닐때
     - 뿌린 본인이 요청이 아닐때, 금지
     - 토큰 타임아웃 체크
     - 정상동작, 조회 정보 응답
     
 ![image](https://user-images.githubusercontent.com/5827617/85914470-00ab4780-b879-11ea-99b3-40c508b0c14b.png)
