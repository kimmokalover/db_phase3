package team8.phase3.service;

import java.util.ArrayList;
import team8.phase3.domain.Review;
import team8.phase3.repository.JdbcLikeInfosRepo;
import team8.phase3.repository.JdbcReviewRepo;
import team8.phase3.repository.Status;

public class ReviewService {

    private final JdbcReviewRepo jdbcReviewRepo;


    public ReviewService(JdbcReviewRepo jdbcReviewRepo) {
        this.jdbcReviewRepo = jdbcReviewRepo;
    }

    public Status addReview(Review review){
        return jdbcReviewRepo.addReview(review);
    }

    public Status deleteReview(Review review){
        return jdbcReviewRepo.deleteReview(review);
    }

    public ArrayList<Review> getReviewInfos(String userId){
        ArrayList<Review> ret = jdbcReviewRepo.getReviewInfos();
        ArrayList<Review> newRet = new ArrayList<>();

        for(Review item : ret){
            if (item.getUserId().equals(userId))newRet.add(item);
        }
        return newRet;
    }
}
