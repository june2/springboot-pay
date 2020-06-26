package com.pay.api.auth.service;

public interface AuthService {
    boolean authenticate(String password, String encPassword);
}
