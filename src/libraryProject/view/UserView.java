package libraryProject.view;

import libraryProject.controller.BookController;
import libraryProject.controller.UserController;
import libraryProject.model.dao.UserDao;

import java.util.Scanner;

import static libraryProject.controller.BookController.addBook;

public class UserView {
    // 1) 싱글톤 세팅
    private UserView() {
    }

    public static final UserView instance = new UserView();

    public static UserView getInstance() {
        return instance;
    }

    // 2) 컨트롤러 싱글톤 가져오기 & 북뷰 싱글톤, 버로우뷰 싱글톤도
    private final UserController userController = UserController.getInstance();
    private final BookView bookView = BookView.getInstance();
    private final BorrowView borrowView = BorrowView.getInstance();

    // * 스캐너
    private final Scanner scan = new Scanner(System.in);

    // 3) 최초화면
    public void index() {
        for (; ; ) {
            System.out.println("더조은 도서관에 오신 것을 환영합니다.");
            System.out.println("1. 로그인 | 2. 회원가입");
            System.out.print("선택 > ");
            int select = scan.nextInt();
            scan.nextLine();
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
        System.out.print("아이디 : ");
        String uid = scan.nextLine();
        System.out.print("비밀번호 : ");
        String upwd = scan.nextLine();
        // 2. 컨트롤러에게 입력받은 값 인수로 전달 후 리턴
        int result = userController.login(uid, upwd);
        // 3. 결과 따른 출력물
        if (result == 0) {
            System.out.println(" [안내] 로그인 성공했습니다.");
            if (uid.equals("admin")) {
                System.out.println("관리자님 환영합니다. 메뉴를 선택해주세요.");
                loginAdminPage();
            } else {
                System.out.println(uid + "님 환영합니다. 메뉴를 선택해주세요.");
                loginPage();
            }
        } else if (result == 1) {
            System.out.println("[경고] 로그인 실패했습니다. 아이디를 입력해주세요.");
        } else if (result == 2) {
            System.out.println("[경고] 로그인 실패했습니다. 비밀번호를 입력해주세요.");
        } else {
            System.out.println("[경고] 로그인 실패했습니다. 아이디와 비밀번호를 다시 확인하세요.");
        }
    }

    // 5) 회원가입 구현
    public void signUp() {
        // 1. 안내와 입력
        System.out.print("아이디 : ");
        String uid = scan.nextLine();
        System.out.print("비밀번호 : ");
        String upwd = scan.nextLine();
        System.out.print("성함 : ");
        String uname = scan.nextLine();
        System.out.print("전화번호 : ");
        String uphone = scan.nextLine();
        // 2. 컨트롤러에게 입력받은 값 인수로 전달 후 리턴
        int result = userController.signUp(uid, upwd, uname, uphone);
        // 3. 결과 따른 출력물
        if (result == 0) {
            System.out.println("[안내] 회원가입에 성공했습니다.");
        } else if (result == 1) {
            System.out.println("[경고] 회원가입에 실패했습니다. 아이디를 입력하세요.");
        } else if (result == 2) {
            System.out.println("[경고] 회원가입에 실패했습니다. 비밀번호를 입력하세요.");
        } else if (result == 3) {
            System.out.println("[경고] 회원가입에 실패했습니다. 이름을 입력하세요.");
        } else if (result == 4) {
            System.out.println(" [경고] 회원가입에 실패했습니다. 휴대전화번호를 입력하세요.");
        }
    }

    // 6-1) 관리자 로그인 후 화면 구현

    // 관리자는 도서등록기능 추가.
    // 일반인은 도서등록기능 X
    public void loginAdminPage() {
        for (; ; ) {
            System.out.println("1. 도서목록 | 2. 도서대출 | 3. 도서반납 | 4. 대출현황 | 5. 로그아웃 | 6. 도서등록");
            System.out.print("선택 > ");
            int select = scan.nextInt();
            scan.nextLine();
            if (select == 1) {
                bookView.getcheckBook();
            }
            if (select == 2) {
                borrowView.addBorrow();
            }
            if (select == 3) {
                borrowView.returnBook();
            }
            if (select == 4) {
                borrowView.showBorrowList();
            }
            if (select == 5) {
                System.out.println("로그아웃 되었습니다. 안녕히 가십시오.");
                break;
            }
            if (select == 6) {
                bookView.addBook();
            }
        }

    }


    // 6-2) 일반인 로그인 후 화면구현
    public void loginPage() {
        for (; ; ) {
            System.out.println("1. 도서목록 | 2. 도서대출 | 3. 도서반납 | 4. 대출현황 | 5. 로그아웃");
            System.out.print("선택 > ");
            int select = scan.nextInt();
            scan.nextLine();
            if (select == 1) {
                bookView.getcheckBook();
            }
            if (select == 2) {
                borrowView.addBorrow();
            }
            if (select == 3) {
                borrowView.returnBook();
            }
            if (select == 4) {
                borrowView.showBorrowList();
            }
            if (select == 5) {
                System.out.println("로그아웃 되었습니다. 안녕히 가십시오.");
                break;
            }
        }
    }

}
