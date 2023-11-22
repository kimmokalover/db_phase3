package team8.phase3.view;

import java.util.ArrayList;
import team8.phase3.domain.Product;

public class ProductView {

    public static void productPage(){
        System.out.println("-----------------상품 정보 페이지 입니다. ---------------------");
        System.out.println("모든 상품들을 보고 싶으면 1번, 좋아요를 누르고 싶으면 2번, 상품 구매를 원하시면 3번을, 장바구니에 추가를 하시려면 4번, 메인페이지로 돌아가려면 5번, 로그아웃은 6번을 누르세요");
    }
    /*
    Long productId;
    Long productCategoryId;
    String brandUserId;
    String productName;
    Long productPrice;
    String productDesc;
     */
    public static void productInfos(ArrayList<Product> products){
        for (Product item : products){
            System.out.printf("상품 아이디: %d, 상품 이름: %s, 상품 가격: %d%n",
                    item.getProductId(), item.getProductName(), item.getProductPrice());
            System.out.printf("상품 설명 : %s\n", item.getProductDesc());
        }
    }

    public static void likeProduct(){
        System.out.println("좋아요 할 상품 아이디를 입력하세요...");
    }

    public static void buyProduct(){
        System.out.println("구매할 상품 아이디를 입력하세요...");
    }
    public static void afterBuyProduct(){
        System.out.println("구매 완료!");
    }

    public static void addShoppingCart(){
        System.out.println("장바구니에 추가할 상품 아이디를 입력하세요");
    }

    public static void afterAddProductInShoppingCart(){
        System.out.println("장바구니 추가 완료!");
    }

}
