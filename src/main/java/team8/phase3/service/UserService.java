package team8.phase3.service;

import team8.phase3.repository.JdbcUserRepo;
import team8.phase3.repository.Status;

public class UserService {

    private final JdbcUserRepo jdbcUserRepo;


    public UserService(JdbcUserRepo jdbcUserRepo) {
        this.jdbcUserRepo = jdbcUserRepo;
    }

    public Status deleteNormalUser(String userId){
        return jdbcUserRepo.deleteNormalUser(userId);
    }

    public Status deleteBrandUser(String userId){
        return jdbcUserRepo.deleteBrandUser(userId);
    }
}
