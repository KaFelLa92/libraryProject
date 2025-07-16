### `libraryProject` 사용자 기능 개선 매뉴얼

이 매뉴얼은 현재 프로젝트의 사용자 관련 기능(로그인, 로그아웃, 세션 관리)이 정상적으로 동작하도록 코드를 수정하는 방법을 안내합니다.

---

#### **1. `UserController.java` 수정**

**목표:** 비즈니스 로직을 View에서 Controller로 옮기고, 로그인 세션을 관리합니다.

**경로:** `src/libraryProject/controller/UserController.java`

**수정 내용:**

```java
package libraryProject.controller;

import libraryProject.model.dao.UserDao;

public class UserController {

    // 1. 싱글톤 패턴 적용
    private UserController() {}
    private static UserController instance = new UserController();
    public static UserController getInstance() {
        return instance;
    }

    // 2. 로그인된 회원 아이디 (세션 역할)
    private String loginId = null;

    // 3. DAO 객체 가져오기
    private UserDao userDao = UserDao.getInstance();

    // 4. 로그인된 회원 아이디 반환
    public String getLoginId() {
        return loginId;
    }

    // 5. 로그인 기능
    // VIEW에서 받은 id, pwd를 DAO로 전달하고, 결과에 따라 loginId를 설정합니다.
    public int login(String uid, String upwd) {
        int result = userDao.login(uid, upwd);
        if (result == 0) {
            this.loginId = uid; // 로그인 성공 시, 컨트롤러에 아이디 저장
        }
        return result;
    }

    // 6. 로그아웃 기능
    // 저장된 loginId를 null로 변경합니다.
    public void logout() {
        this.loginId = null;
        System.out.println("[안내] 로그아웃 되었습니다.");
    }

    // 7. 회원가입 기능
    // VIEW에서 받은 정보를 DAO로 전달합니다.
    public int signUp(String uid, String upwd, String uname, String uphone) {
        return userDao.signUp(uid, upwd, uname, uphone);
    }
}
```

---

#### **2. `UserDao.java` 수정 (선택 사항)**

**목표:** `login` 메소드에서 불필요한 객체 생성을 제거하여 코드를 최적화합니다.

**경로:** `src/libraryProject/model/dao/UserDao.java`

**기존 코드 (login 메소드):**
```java
    public int login(String uid, String upwd) {
        // 객체화
        UserDto userDto = new UserDto(uid, upwd);

        //유효성 검사
        if (uid == null || uid.isEmpty()) {
            return 1;
        }
        // ...
```

**수정 후 코드 (login 메소드):**
```java
    public int login(String uid, String upwd) {
        //유효성 검사
        if (uid == null || uid.isEmpty()) {
            return 1;
        }
        if (upwd == null || upwd.isEmpty()) {
            return 2;
        }
        // 유효성 검사 : for문으로
        for (UserDto user : userDB) {
            if (user.getUid().equals(uid) && user.getUpwd().equals(upwd)) {
                return 0; //아이디 비번 일치
            }
        }
        return 3; // 아이디 비번 불일치
    }
```
*주: `logout` 메소드는 컨트롤러에서 세션을 관리하므로 DAO에서는 구현할 필요가 없습니다.*

---

#### **3. `UserView.java` 수정**

**목표:** Controller와 연동하여 UI와 로직을 분리하고, 로그인 상태에 따라 다른 메뉴를 보여줍니다.

**경로:** `src/libraryProject/view/UserView.java`

**수정 내용:**

```java
package libraryProject.view;

import libraryProject.controller.UserController;
import java.util.Scanner;

public class UserView {
    // 1) 싱글톤 세팅
    private UserView() {}
    public static final UserView instance = new UserView();
    public static UserView getInstance() {
        return instance;
    }

    // 2) 컨트롤러 싱글톤 가져오기
    private UserController userController = UserController.getInstance();

    // * 스캐너
    private Scanner scan = new Scanner(System.in);

    // 3) 최초화면
    public void index() {
        while (true) {
            // 로그인 상태에 따라 다른 화면 출력
            if (userController.getLoginId() == null) {
                showLoginMenu(); // 로그인 안된 상태 메뉴
            } else {
                showUserMenu(); // 로그인 된 상태 메뉴
            }
        }
    }

    // 4) 로그인 전 메뉴
    private void showLoginMenu() {
        System.out.println("더조은 도서관에 오신 것을 환영합니다.");
        System.out.println("1. 로그인 | 2. 회원가입 | 3. 프로그램 종료");
        System.out.print("선택 > ");
        int select = scan.nextInt();
        scan.nextLine(); // nextInt() 버퍼 비우기

        if (select == 1) {
            login();
        } else if (select == 2) {
            signUp();
        } else if (select == 3) {
            System.out.println("[안내] 프로그램을 종료합니다.");
            System.exit(0);
        } else {
            System.out.println("[경고] 잘못된 입력입니다.");
        }
    }

    // 5) 로그인 후 메뉴
    private void showUserMenu() {
        System.out.println("
[" + userController.getLoginId() + "님 환영합니다]");
        System.out.println("1. 도서 대여 | 2. 도서 반납 | 3. 내 정보 | 4. 로그아웃");
        System.out.print("선택 > ");
        int select = scan.nextInt();
        scan.nextLine(); // nextInt() 버퍼 비우기

        if (select == 1) {
            // BookView 연동 (추후 구현)
            System.out.println("도서 대여 로직을 연결하세요.");
        } else if (select == 2) {
            // BookView 연동 (추후 구현)
            System.out.println("도서 반납 로직을 연결하세요.");
        } else if (select == 3) {
            // 내 정보 보기 로직 (추후 구현)
            System.out.println("내 정보 보기 로직을 연결하세요.");
        } else if (select == 4) {
            userController.logout();
        } else {
            System.out.println("[경고] 잘못된 입력입니다.");
        }
    }

    // 6) 로그인 구현
    public void login() {
        System.out.print("아이디 : ");
        String uid = scan.nextLine();
        System.out.print("비밀번호 : ");
        String upwd = scan.nextLine();

        int result = userController.login(uid, upwd);

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

    // 7) 회원가입 구현
    public void signUp() {
        System.out.print("아이디 : ");
        String uid = scan.nextLine();
        System.out.print("비밀번호 : ");
        String upwd = scan.nextLine();
        System.out.print("성함 : ");
        String uname = scan.nextLine();
        System.out.print("전화번호 : ");
        String uphone = scan.nextLine();

        int result = userController.signUp(uid, upwd, uname, uphone);

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
}
```

---

#### **4. 최종 확인**

1.  `AppStart.java`의 `main` 메소드를 실행합니다.
2.  회원가입을 진행합니다.
3.  가입한 정보로 로그인을 시도합니다.
4.  로그인 성공 시, 새로운 메뉴(`도서 대여`, `로그아웃` 등)가 나타나는지 확인합니다.
5.  로그아웃을 선택하면 다시 초기 로그인/회원가입 메뉴로 돌아가는지 확인합니다.
