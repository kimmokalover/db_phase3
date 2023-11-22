package team8.phase3.view;

public class LoginView {

    public static void loginForm(){

        System.out.println("--------- 일반 사용자이면 1번, 브랜드 사용자이면 2번, 관리자이면 3번을 눌러주세요----------");
    }

    public static void userLoginForm(){
        System.out.println("--------------------------아이디, 비밀번호를 공백을 기준으로 입력해주세요--------------------------");
    }

    public static void successLogin(){
        System.out.println("--------------------- 로그인 성공!! ----------------------");
    }

    public static void failLogin(){
        System.out.println("--------------------- 로그인 실패!! ----------------------");
    }
}
