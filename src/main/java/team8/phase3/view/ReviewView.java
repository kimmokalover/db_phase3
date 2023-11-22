package team8.phase3.view;

import java.util.ArrayList;
import team8.phase3.domain.Review;

public class ReviewView {
    public static void reviewPage(){
        System.out.println("--------------- 리뷰 페이지 입니다. ------------------");
    }


    public static void printReviews(ArrayList<Review> reviews, String id){
        System.out.printf("%s 님의 리뷰 입니다.\n", id);
        for (Review item : reviews){
            System.out.printf("등록된 상품 번호 : %s 등록 시간 : %s 등록된 글 : %s\n",item.getProductId(), item.getReviewDate().toString(), item.getReview());

        }
    }

    public static void addReview(){
        System.out.println("리뷰를 등록할까요? 등록하려면 1번 아니면 2번을 눌러주세요");

    }
    public static void productReviewId(){
        System.out.println("리뷰를 등록할 상품 번호와 리뷰 내용을 공백을 기준으로 입력하세요");
    }

    public static void createReview(){
        System.out.println("리뷰를 작성해주세요.");
    }

    public static void afterAddReview(){
        System.out.println("리뷰 작성 완료!!");
    }

}
