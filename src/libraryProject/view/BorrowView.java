package libraryProject.view;

import libraryProject.controller.BorrowController;
import libraryProject.model.dto.BorrowDto;

import java.util.ArrayList;
import java.util.Scanner;

public class BorrowView {
    private Scanner scan = new Scanner(System.in);
    private static final BorrowView instance = new BorrowView();
    public static BorrowView getInstance() { return instance; }

    // 메인 메뉴
    public void index() {
        while (true) {
            System.out.println("=========================================");
            System.out.println(" 1. 도서대출 | 2. 도서반납 | 3. 도서대출현황");
            System.out.println("=========================================");
            System.out.print("선택 > ");
            int choose = scan.nextInt();
            scan.nextLine(); // 개행 제거

            if (choose == 1) {
                addBorrow(); // 도서 대출
            } else if (choose == 2) {
                returnBook(); // 도서 반납
            } else if (choose == 3) {
                showBorrowList(); // 도서 대출 현황
            } else {
                System.out.println("[경고] 올바른 번호를 선택하세요.");
            }
        }
    }

    // 도서 대출 등록
    public void addBorrow() {
        System.out.print("도서 대출 제목 입력: ");
        String content = scan.nextLine();

        boolean result = BorrowController.addborrow(content);

        if (result) {
            System.out.println("[도서 대출] 성공");
        } else {
            System.out.println("[도서 대출] 실패");
        }
    }

    public void returnBook() {
        System.out.print("반납할 도서 제목 입력: ");
        String bookName = scan.nextLine();

        boolean result = BorrowController.returnBook(bookName);

        if (result) {
            System.out.println("[도서 반납] 성공");
        } else {
            System.out.println("[도서 반납] 실패");
        }
    }

    // 도서 대출 현황 출력
    public void showBorrowList() {
        System.out.println("============= 도서 대출 목록 =============");
        ArrayList<BorrowDto> result = BorrowController.getBorrow();

        for (BorrowDto bookborrow : result) {
            System.out.println("도서 제목: " + bookborrow.getName());
        }
    }
}