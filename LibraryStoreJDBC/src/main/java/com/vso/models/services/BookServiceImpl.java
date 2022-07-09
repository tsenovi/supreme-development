package com.vso.models.services;

import com.vso.models.dto.BookDTO;
import com.vso.models.entities.Book;
import com.vso.models.entities.Status;
import com.vso.models.repositories.BookDAO;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDAO repository;

    public BookServiceImpl(BookDAO bookDAO) {
        this.repository = bookDAO;
    }

    @Override
    public List<Book> getInitBooks() {
        return repository.getAllBooks();
    }

    @Override
    public List<Book> findBooksByName(String bookName) {
        BookDTO bookDTO = new BookDTO(bookName);
        return repository.getBooksByName(bookDTO);
    }

    @Override
    public boolean findBookByName(String userChoice) {
        BookDTO bookDTO = new BookDTO(userChoice);
        Book book = repository.getBookByName(bookDTO);
        if (book != null && book.getStatus().equals(Status.AVAILABLE)) {
            book.setStatus(Status.RENTED);
            repository.updateBook(book);
            return true;
        } else if (book != null && book.getStatus().equals(Status.RENTED)){
            book.setStatus(Status.AVAILABLE);
            repository.updateBook(book);
            return true;
        }
        return false;
    }
}
