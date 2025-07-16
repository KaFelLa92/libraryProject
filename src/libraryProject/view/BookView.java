package libraryProject.view;

import libraryProject.controller.BookController;
import libraryProject.model.dto.BookDto;

import java.util.ArrayList;
import java.util.Scanner;

public class BookView {
    private BookView() {
    }

    private static final BookView bookView = new BookView();

    public static BookView getInstance() {
        return bookView;
    }

    private BookController BCL = BookController.getInstance();

    private Scanner sc = new Scanner(System.in);

    public void ii() {
        for (; ; ) {
            System.out.println("========");
            System.out.println("1. Add Book | 2. inventory Book | 3. check book ");
            System.out.println(" select > ");
            int choice = sc.nextInt();
            if (choice == 1) {
                addBook();
            } else if (choice == 2) {
                getBooks();
            } else if (choice == 3) {
                getcheckBook();
            }
        }
    }

    public void addBook() {
        System.out.print("Add Book Name(등록할 책) : ");
        String bookName = sc.next();
        System.out.print(" Book Author name(저자): ");
        String bookAuthor = sc.next();
        boolean result = BookController.addBook(bookName, bookAuthor);

        if (result == true) {
            System.out.println("책 등록 성공");
        } else {
            System.out.println("책 등록 실패");
        }
    }

    public void getBooks() {
        System.out.println("=================");
        ArrayList<BookDto> books = BCL.getBooks();
        for (int i = 0; i < books.size(); i++) {
            BookDto book = books.get(i);
            System.out.println("등록하신 책 :" + book.getBname());
            System.out.println("저자 : " + book.getBwriter());
            System.out.println("-------------------------");
        }
    }

    public void getcheckBook() {
        System.out.print("검색할 책 : ");
        String bookName = sc.next();
        ArrayList<BookDto> books = BCL.getBooks();
        if (!bookName.equals(bookName)) {
            System.out.println("다시 검색해주세요.");
            getcheckBook();
        } else {
            boolean result = false;
            for (int i = 0; i < books.size(); i++) {
                BookDto book = books.get(i);
                if (book.getBname().equals(bookName)) {
                    System.out.println("검색하신 책을 찾았습니다 :" + book.getBname());
                    System.out.println("저자 :  " + book.getBwriter());
                    result = true;
                }
                if (!result) {
                    System.out.println("검색하신 책을 찾지 못했습니다. ");
                }
                return;
            }
        }
    }
}
