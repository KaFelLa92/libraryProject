//package libraryProject.model.dao;
//
//import libraryProject.model.dto.BorrowDto;
//
//import java.util.ArrayList;
//
//public class BorrowDao {
//
//    private static final BorrowDao instance = new BorrowDao();
//
//    public static BorrowDao getInstance() {
//        return instance;
//    }
//
//    private ArrayList<BorrowDto> borrowList = new ArrayList<>();
//
//    public boolean addBorrow(String bookBorrow) {
//        return borrowList.add(new BorrowDto(bookBorrow));
//    }
//
//    public ArrayList<BorrowDto> getBorrow() {
//        return borrowList;
//    }
//
//    public ArrayList<BorrowDto> getArrayList() {
//        return borrowList;
//    }
//    public boolean removeBorrow(String bookName) {
//        for (BorrowDto dto : borrowList) {
//            if (dto.getName().equals(bookName)) {
//                borrowList.remove(dto);
//                return true; // 성공적으로 삭제
//            }
//        }
//        return false; // 해당 도서 없음
//    }
//
//}