package team8.phase3.service;

import team8.phase3.domain.BrandUser;
import team8.phase3.domain.NormalUser;
import team8.phase3.repository.JdbcJoinRepo;
import team8.phase3.repository.Status;

public class JoinService {

    private final JdbcJoinRepo joinRepo;


    public JoinService(JdbcJoinRepo joinRepo) {
        this.joinRepo = joinRepo;
    }

    public Status join(NormalUser normalUser){
        Status status = joinRepo.checkDuplicate(normalUser.getId(), 1);
        if (status == Status.FAIL)return status;
        status = joinRepo.join(normalUser);
        return status;
    }

    public Status join(BrandUser brandUser){
        Status status = joinRepo.checkDuplicate(brandUser.getId(), 2);
        if (status == Status.FAIL)return status;
        status = joinRepo.join(brandUser);
        return status;
    }

}
