package libraryProject.controller;

import libraryProject.model.dao.BorrowDao;
import libraryProject.model.dto.BorrowDto;

import java.util.ArrayList;

public class BorrowController {

    //Dao 호출
    private static BorrowDao dao = BorrowDao.getInstance();

    // View에서 넘겨받은 int 값을 String으로 바꿔서 Dao로 넘김
    public static boolean addborrow(String bookborrow) {
        return dao.addBorrow(bookborrow);  // 대문자 B
    }
    public static boolean returnBook(String bookName) {
        return dao.removeBorrow(bookName); // 아래에서 구현할 dao 메서드
    }

    public static ArrayList<BorrowDto> getBorrow() {
        return dao.getArrayList();
    }
}
