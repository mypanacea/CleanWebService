package web.controller;

import common.util.InputStringUtil;
import domain.service.impl.DefaultBookService;
import domain_model.DomainBook;
import web.component.RequestMapping;
import web.template.HtmlMarker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookController {
    private static BookController bookController;

    private BookController() {
    }

    public static BookController getInstance() {
        if (bookController == null) {
            synchronized (BookController.class) {
                if (bookController == null) {
                    bookController = new BookController();
                }
            }
        }
        return bookController;
    }

    @RequestMapping(path = "/index")
    @SuppressWarnings("unused")
    public void index(final Map<String, String> paramMap) {
        Map<String, List<DomainBook>> map = new HashMap<>();
        map.put("book", DefaultBookService.getInstance().getCollection());
        HtmlMarker.getInstance().makeTemplate("index", map);
    }

    @RequestMapping(path = "/add")
    @SuppressWarnings("unused")
    public void addBook(final Map<String, String> paramMap) {
        DefaultBookService.getInstance().addBook(paramMap);
        Map<String, List<DomainBook>> map = new HashMap<>();
        map.put("book", DefaultBookService.getInstance().getCollection());
        HtmlMarker.getInstance().makeTemplate("index", map);
    }



}
