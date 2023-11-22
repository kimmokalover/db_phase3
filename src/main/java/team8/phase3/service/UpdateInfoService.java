package team8.phase3.service;

import team8.phase3.repository.JdbcLoginRepo;
import team8.phase3.repository.JdbcUpdateUserInfoRepo;
import team8.phase3.repository.Status;

public class UpdateInfoService {

    private final JdbcUpdateUserInfoRepo updateUserInfoRepo;


    public UpdateInfoService(JdbcUpdateUserInfoRepo updateUserInfoRepo) {
        this.updateUserInfoRepo = updateUserInfoRepo;
    }

    public Status updateInfo(String attributeName, String attribute, String normalUserId){
        return updateUserInfoRepo.updateUserInfo(attributeName, attribute, normalUserId);
    }
    public Status updateInfo(String attributeName, Long attribute, String normalUserId){
        return updateUserInfoRepo.updateUserInfo(attributeName, attribute, normalUserId);
    }
}
