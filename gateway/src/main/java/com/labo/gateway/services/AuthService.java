package com.labo.gateway.services;

import jwtSecurity.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}