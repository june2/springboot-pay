## Reservation app java server api

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

### ERD 설계
![image](https://user-images.githubusercontent.com/5827617/85913495-5e876180-b870-11ea-9047-b57f99ee3335.png)

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

### 설계 가설 설정
- 뿌리기 금액은 뿌리기 인원에 정확히 나누어 떨어져야한다. 아니면 에러발생
- 방 인원수에 상관없이 뿌리기 인원을 설정할수 있다. 
  - 뿌리기 수가 방 인원수가 초과 가능  

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
     - 정상 동작, 토큰리턴 길이 3
  - 조회 API 테스트
     - 유효한 방이 아닐때
     - 유효한 토큰이 아닐때
     - 뿌린 본인이 요청할때, 금지
     - 토큰 타임아웃 체크
     - 이미 가져간 유저, 에러
     - 정상동작, 가져간 금액 정보 응답
  - 받기 API 테스트
     - 유효한 방이 아닐때
     - 유효한 토큰이 아닐때
     - 뿌린 본인이 요청이 아닐때, 금지
     - 토큰 타임아웃 체크
     - 정상동작, 조회 정보 응답