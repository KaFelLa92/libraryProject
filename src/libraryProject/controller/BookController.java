package libraryProject.controller;

import libraryProject.model.dao.BookDao;
import libraryProject.model.dto.BookDto;

import java.util.ArrayList;

public class BookController {
    private BookController( ) { }
    private static final BookController instance = new BookController();
    public static BookController getInstance() { return instance; }

    private static BookDao bookDao = BookDao.getInstance();

    public static boolean addBook(String bname, String bwriter) {
        boolean result = false;
        result = bookDao.addBook(bname, bwriter);
        return result;
    }

    public ArrayList<BookDto> getBooks(){
        return bookDao.getBooks();
    }
}
