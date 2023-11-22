package team8.phase3.view;

import team8.phase3.domain.Rank;

public class JoinView {

    public static void userChoice(){
        System.out.println("---------------------------------");
        System.out.println("   안녕하세요. 과잠사 프로그램입니다.");
        System.out.println("로그인은 1번, 회원가입은 2번을 눌러주세요.");
    }

    public static void joinForm(){
        System.out.println("----------일반 사용자 이면 1번, 브랜드 사용자 이면 2번을 입력해주세요.----------");
    }

    public static void normalUserJoinForm(){
        System.out.println("아이디, 비밀번호, 이메일, 전화번호, 이름, 나이, 주소를 공백을 기준으로 입력하세요.");
    }
    public static void brandUserJoinForm(){
        System.out.println("아이디, 비밀번호, 이메일, 전화번호, 회사이름, 회사 전화번호, 회사 주소, 사업자 번호, 회사 도메인을 공백을 기준으로 입력하세요");
    }

    public static void duplicateId(){
        System.out.println("----------------- key constraint를 위반합니다. 아이디, 전화 번호와 같은 항목이 중복됬을 수 있습니다. ----------------------");
    }

    public static void joinSuccess(){
        System.out.println("---------------- 회원가입 성공!!----------------");
    }

}
