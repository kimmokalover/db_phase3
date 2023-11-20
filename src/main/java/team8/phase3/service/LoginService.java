package team8.phase3.service;

import team8.phase3.repository.JdbcLoginRepo;

public class LoginService {
    private final JdbcLoginRepo loginRepo;


    public LoginService(JdbcLoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }
}
