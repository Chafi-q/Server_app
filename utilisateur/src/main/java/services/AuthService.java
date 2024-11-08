package services;

import jwtSecurity.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}