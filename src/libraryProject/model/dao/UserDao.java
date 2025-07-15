package libraryProject.model.dao;

import libraryProject.model.dto.UserDto;

import java.util.ArrayList;

public class UserDao {

    // 1) 싱글톤 세팅
    private UserDao() {
    }

    public static final UserDao instance = new UserDao();

    public static UserDao getInstance() {
        return instance;
    }

    // 2) 여러 회원정보테이블을 저장할 리스트 선언
    private ArrayList<UserDto> userDB = new ArrayList<>();

    // 3) 회원가입 기능 구현
    public int signUp(String uid, String upwd, String uname, String uphone) {
        // 기본값
        int result = 1; // '가입 실패. 아이디 입력하세요'가 기본
        // 객체화
        UserDto userDto = new UserDto(uid, upwd, uname, uphone);
        // 유효성 검사
        if (upwd == null) {   // 비번 공란?
            result = 2;
        } else if (uname == null) {
            result = 3;
        } else if (uphone == null) {
            result = 4;
        }
        if (uid != null && upwd != null && uname != null && uphone != null) {
            // 객체 넣어라
            userDB.add(userDto);
            result = 0; // 가입 성공
        }
        return result;
    }

    // 4) 로그인 기능 구현
    public int login(String uid, String upwd) {
        // 기본값
        int result = 1; // 로그인 실패. 아이디 입력하세요가 기본
        // 객체화
        UserDto userDto = new UserDto(uid, upwd);
        // 유효성 검사 : for문으로
        for (UserDto user : userDB) {
            if (user.getUid().equals(uid) && user.getUpwd().equals(upwd)) {
                return 0;
            } else if (upwd == null) {
                return 2;
            } else if (!user.getUid().equals(uid) || !user.getUpwd().equals(upwd)) {
                return 3;
            }
        } // for end
        return result;
    }

    // 5) 로그아웃 기능 구현 : 누구세요?

}
