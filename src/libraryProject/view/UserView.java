package libraryProject.view;

import libraryProject.model.dao.UserDao;

import java.util.Scanner;

public class UserView {
    // 1) 싱글톤 세팅
    private UserView() {
    }

    public static final UserView instance = new UserView();

    public static UserView getInstance() {
        return instance;
    }

    // 2) dao 싱글톤 가져오기
    private UserDao userDao = UserDao.getInstance();

    // * 스캐너
    private Scanner scan = new Scanner(System.in);

    // 3) 최초화면
    public void index() {
        for (; ; ) {
            System.out.println("더조은 도서관에 오신 것을 환영합니다.");
            System.out.println("1. 로그인 | 2. 회원가입");
            System.out.print("선택 > ");
            int select = scan.nextInt();
            if (select == 1) {
                login();
            }
            if (select == 2) {
                signUp();
            }
        }
    }

    // 4) 로그인 구현
    public void login() {
        // 1. 안내와 입력
        System.out.println("아이디 : ");
        String uid = scan.next();
        System.out.println("비밀번호 : ");
        String upwd = scan.next();
        // 2. dao에게 입력받은 값 인수로 전달 후 리턴
        int result = userDao.login(uid, upwd);
        // 3. 결과 따른 출력물
        if (result == 0) {
            System.out.println(" [안내] 로그인 성공했습니다.");
        } else if (result == 1) {
            System.out.println("[경고] 로그인 실패했습니다. 아이디를 입력해주세요.");
        } else if (result == 2) {
            System.out.println("[경고] 로그인 실패했습니다. 비밀번호를 입력해주세요.");
        } else {
            System.out.println("[경고] 로그인 실패했습니다. 아이디와 비밀번호를 다시 확인하세요.");
        }
    }

    // 5) 회원가입 구현
    public void signUp(){
        // 1. 안내와 입력
        System.out.println("아이디 : ");
    }
}
