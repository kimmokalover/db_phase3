package team8.phase3.view;

import java.util.ArrayList;
import team8.phase3.domain.ShoppingCart;
import team8.phase3.service.ShoppingCartService;

public class ShoppingCartView {

    public static void shoppingCartPageStart(){
        System.out.println("------------------- 장바구니 페이지 입니다. --------------------------");
        System.out.println("장바구니에 있는 상품 삭제는 1번, 장바구니에 담긴 물품 구매는 2번, 장바구니 정보 출력은 3번을 누르세요");
    }
    public static void deleteProduct(){
        System.out.println("장바구니에 담긴 물품들을 삭제합니다.");
        System.out.println("장바구니에 담긴 물품들을 삭제했습니다.");
    }
    public static void buyProductsInShoppingCart(){
        System.out.println("장바구니에 있는 모든 상품을 구매했습니다.");
    }
    public static void printInfos(ArrayList<ShoppingCart> shoppingCarts, String id){
        System.out.printf("%s: 님의 장바구니 상품들을 출력합니다.\n", id);
        for(ShoppingCart item : shoppingCarts){
            System.out.printf("장바구니 번호 : %s, 상품 번호 : %s, 상품 갯수 : %s\n", item.getOrderId(), item.getProductId().toString(), item.getAmount().toString());
        }
    }
}
