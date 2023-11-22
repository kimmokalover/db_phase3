package team8.phase3.service;

import team8.phase3.repository.JdbcLoginRepo;
import team8.phase3.repository.Status;

public class LoginService {
    private final JdbcLoginRepo loginRepo;


    public LoginService(JdbcLoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }

    public Status normalLogin(String normalUserId, String passWord){
        return loginRepo.normalUserLogin(normalUserId, passWord);
    }

    public Status brandLogin(String brandUserId, String passWord){
        return loginRepo.brandUserLogin(brandUserId, passWord);
    }

    public Status adminLogin(String adminId, String passWord){
        return loginRepo.adminLogin(adminId, passWord);
    }
}
