package com.vso.models.services;

import com.vso.models.entities.Book;

import java.util.List;

public interface BookService {
    List<Book> getInitBooks();

    List<Book> findBooksByName(String bookName);

    boolean findBookByName(String userChoice);
}
