package com.vso.presenters;

import com.vso.views.BookView;
import com.vso.models.services.BookService;

public class BookPresenterImpl implements BookPresenter {

    private final BookService bookService;
    private final BookView bookView;

    public BookPresenterImpl(BookService bookService, BookView bookView) {
        this.bookService = bookService;
        this.bookView = bookView;
    }

    @Override
    public void start() {
        while (true) {
            bookView.show("Library Store");
            bookView.printList(bookService.getInitBooks());
            bookView.getOptions();
            int userOptions = bookView.getDecimalInput();
            runOptions(userOptions);
        }
    }

    private void runOptions(int userOptions) {
        switch (userOptions) {
            case 1 -> rentBookProcess();
            case 2 -> returnBookProcess();
        }
    }

    private void returnBookProcess() {
        String bookName = bookView.getTextInput("Book name: ");
        bookView.printList(bookService.findBooksByName(bookName));
        String userChoice = bookView.getTextInput("Book to return: ");
        boolean isReturned = bookService.findBookByName(userChoice);
        if(isReturned) bookView.show("Successful operation.");
        else bookView.show("No such book exists!");
    }

    private void rentBookProcess() {
        String bookName = bookView.getTextInput("Book name: ");
        bookView.printList(bookService.findBooksByName(bookName));
        String userChoice = bookView.getTextInput("Book to rent: ");
        boolean isRented = bookService.findBookByName(userChoice);
        if(isRented) bookView.show("Successful operation.");
        else bookView.show("No such book exists!");
    }
}
