package team8.phase3.view;

public class MainPageView {
    public static void normalUserMainPage(){
        System.out.println("----------------- 일반 유저 메인 페이지 입니다.--------------------");
        System.out.println("상품 정보를 보고 싶으면 1번, 자신이 좋아요 한 정보를 보고 싶으면 2번, 자신이 작성한 리뷰 확인 3번, 장바구니 페이지는 4번, 로그아웃은 5번을 눌러주세요");
    }

    public static void brandUserMainPage(){
        System.out.println("----------------- 브랜드 유저 메인 페이지 입니다.--------------------");
        System.out.println("상품 추가 1번, 상품 수정 2번, 상품 삭제 3번, 로그아웃은 4번을 누르세요");
    }

    public static void adminUserMainPage(){
        System.out.println("----------------- 관리자 페이지 입니다. ---------------------------");
        System.out.println("넌 그냥 아무것도 하지 마세요");
    }

    public static void rollBackToMainPage(){
        System.out.println("------------------메인페이지로 돌아갑니다---------------------");
    }
}
