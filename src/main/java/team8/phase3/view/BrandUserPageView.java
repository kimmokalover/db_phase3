package team8.phase3.view;

public class BrandUserPageView {

    public static void addProductMessage(){
        System.out.println("상품 추가를 하려면, 카테고리 번호, 상품 명, 상품 가격, 상품 설명을 공백을 기준으로 입력하세요");
    }

    public static void afterAddProductMessage(){
        System.out.println("상품 추가 완료!!");
    }

    public static void deleteProductMessage(){
        System.out.println("삭제할 상품 번호를 알려주세요");
    }

    public static void afterDeleteProductMessage(){
        System.out.println("상품 삭제 완료!");
    }

    public static void modifyProductMessage(){
        System.out.println("상품을 수정하려면 변경할 에트리뷰트 명과, 새로운 값, 상품 아이디를 공백 기준으로 입력하세요");
    }
    public static void afterModifyProductMessage() {
        System.out.println("상품 수정이 완료되었습니다.");
    }

}
