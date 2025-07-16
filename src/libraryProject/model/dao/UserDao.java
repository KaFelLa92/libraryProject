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
        // 객체화
        UserDto userDto = new UserDto(uid, upwd, uname, uphone);
        // 유효성 검사
        if (uid == null || uid.trim().isEmpty()) {   // 비번 공란?
            return 1;
        }
        if (upwd == null || upwd.trim().isEmpty()) {
            return 2;
        }
        if (uname == null || uname.trim().isEmpty()) {
            return 3;
        }
        if (uphone == null || uphone.trim().isEmpty()) {
            return 4;
        }
        // DB저장
        userDB.add(userDto);
        return 0; // 가입 성공
    }

    // 4) 로그인 기능 구현
    public int login(String uid, String upwd) {
        // 객체화
        UserDto userDto = new UserDto(uid, upwd);

        //유효성 검사
        if (uid == null || uid.trim().isEmpty()) {
            return 1;
        }
        if (upwd == null || upwd.trim().isEmpty()) {
            return 2;
        }
        // 유효성 검사 : for문으로
        for (UserDto user : userDB) {
            if (user.getUid().equals(uid) && user.getUpwd().equals(upwd)) {
                return 0; //아이디 비번 일치
            } // for end

        }
        return 3; // 아이디 비번 불일치


    }

    // 5) 로그아웃 기능 구현 : 누구세요?

}


