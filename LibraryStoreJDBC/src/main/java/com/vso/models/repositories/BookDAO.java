package com.vso.models.repositories;

import com.vso.models.dto.BookDTO;
import com.vso.models.entities.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks();

    List<Book> getBooksByName(BookDTO bookDTO);

    Book getBookByName(BookDTO bookDTO);

    void insertBooks();

    void createTableBooks();

    void updateBook(Book book);
}
