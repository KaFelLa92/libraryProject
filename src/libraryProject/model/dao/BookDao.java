package libraryProject.model.dao;

import libraryProject.model.dto.BookDto;

import java.util.ArrayList;

public class BookDao {
    private BookDao() {
    }

    private static final BookDao instance = new BookDao();

    public static BookDao getInstance() {
        return instance;
    }

    private ArrayList<BookDto> books = new ArrayList<>();


    public boolean addBook(String bname, String bwriter ) {
        for (BookDto book : books) {
            if (book.getBname().equals(bname) && book.getBwriter().equals(bwriter)) {
                return false;
            }
        }
        BookDto bookDto = new BookDto(bname, bwriter);
        books.add(bookDto);
        return true;
    }
    public ArrayList<BookDto> getBooks(){
        return books;
    }
}
