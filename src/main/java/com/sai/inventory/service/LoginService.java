package com.sai.inventory.service;

import com.sai.inventory.domain.login.request.LoginRequest;

public interface LoginService {
    Object authenticateUser(LoginRequest req);
}
