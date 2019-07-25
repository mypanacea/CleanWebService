package domain.service;

import domain_model.DomainBook;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<DomainBook> getCollection();

    void addBook(Map<String, String> paramMap);

    void deleteBookById(long id);
}
