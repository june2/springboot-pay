package com.pay.util;

import java.time.LocalDateTime;

public class Date {
    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    /**
     * 유효시간 체크
     * @param from 언제부터
     * @param validTime 몇분간
     * @return boolean
     */
    public static boolean isValidTime(LocalDateTime from, long validTime) {
        LocalDateTime now = LocalDateTime.now();
        from = from.plusMinutes(validTime);
        return from.isAfter(now);
    }
}
