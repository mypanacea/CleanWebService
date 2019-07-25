package domain.service.impl;

import domain.service.BookService;
import domain_model.DomainBook;
import web.controller.BookController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultBookService implements BookService {
    private static BookService bookService;

    private List<DomainBook> bookList = new ArrayList<>();

    private DefaultBookService() {
    }

    public static BookService getInstance() {
        if (bookService == null) {
            synchronized (BookController.class) {
                if (bookService == null) {
                    bookService = new DefaultBookService();
                }
            }
        }
        return bookService;
    }

    @Override
    public List<DomainBook> getCollection() {
        System.out.println("get collection " + bookList);
        return bookList;
    }

    @Override
    public void addBook(Map<String, String> paramMap) {
        final DomainBook domainBook = new DomainBook(paramMap.get("id"), paramMap.get("author"), paramMap.get("title"));
        bookList.add(domainBook);
        System.out.println("add book " + domainBook);
    }

    @Override
    public void deleteBookById(long id) {
        //todo #1
    }
}
