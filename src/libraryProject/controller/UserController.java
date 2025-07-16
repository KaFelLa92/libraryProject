package libraryProject.controller;

import libraryProject.model.dao.UserDao;

public class UserController {

    // 1) 싱글톤
    private UserController(){}
    private static final UserController instance = new UserController();
    public static UserController getInstance() {
        return instance;
    }

    // * 회원 아이디 상태
    private String loginId = null;  // id 값이 있을 때 로그인 상태, null일때 로그아웃상태

    // 2) Dao 싱글톤 가져오기
    private UserDao userDao = UserDao.getInstance();

    // 3) 회원가입 컨트롤
    public int signUp(String uid, String upwd, String uname, String uphone){
        return userDao.signUp(uid, upwd, uname, uphone);
    }

    // 4) 로그인 컨트롤
    public int login(String uid, String upwd){
        int result = userDao.login(uid, upwd);
        if (result == 0) {
            this.loginId = uid;
        }
        return result;
    }

    // 5) 로그아웃 컨트롤
    public void logout(){
        this.loginId = null;
        System.out.println("[안내] 로그아웃 되었습니다.");
    }
}
