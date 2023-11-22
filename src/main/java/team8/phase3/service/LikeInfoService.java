package team8.phase3.service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import team8.phase3.domain.LikeInfo;
import team8.phase3.repository.JdbcLikeInfosRepo;
import team8.phase3.repository.Status;

public class LikeInfoService {

    private final JdbcLikeInfosRepo jdbcLikeInfosRepo;


    public LikeInfoService(JdbcLikeInfosRepo jdbcLikeInfosRepo) {
        this.jdbcLikeInfosRepo = jdbcLikeInfosRepo;
    }

    public Status doLike(String userId, Long productId){
        LikeInfo likeInfo = new LikeInfo(Long.valueOf((LocalDate.now().toString() + userId + productId.toString()).hashCode()), userId, productId);
        return jdbcLikeInfosRepo.doLike(likeInfo);
    }

    public Status unDoLike(LikeInfo likeInfo){
        return jdbcLikeInfosRepo.unDoLike(likeInfo);
    }

    public ArrayList<LikeInfo> getLikeInfos(String userId){
        ArrayList<LikeInfo> ret = jdbcLikeInfosRepo.getLikeInfos(userId);

        return ret;
    }
}
