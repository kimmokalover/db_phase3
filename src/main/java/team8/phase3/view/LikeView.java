package team8.phase3.view;

import java.util.ArrayList;
import team8.phase3.domain.LikeInfo;
import team8.phase3.domain.Product;

public class LikeView {

    public static void afterDoLike(){
        System.out.println("좋아요 등록!");
    }


    public static void checkLikeList(ArrayList<Product> arr, String userId){
        System.out.printf("%s 님이 좋아요한 상품 목록들 입니다.\n", userId);
        for(Product item : arr){
            System.out.printf("상품 이름 : %s 상품 가격 : %s\n", item.getProductName(), item.getProductPrice().toString());
        }
    }
}
