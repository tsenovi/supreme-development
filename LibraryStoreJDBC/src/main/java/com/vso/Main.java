package com.vso;

import com.vso.models.repositories.BookDAOImpl;
import com.vso.models.services.BookServiceImpl;
import com.vso.models.utils.DatabaseConnectorImpl;
import com.vso.presenters.BookPresenter;
import com.vso.presenters.BookPresenterImpl;
import com.vso.views.BookViewImpl;

public class Main {

    public static void main(String[] args) {
        BookPresenter bookPresenter = new BookPresenterImpl(
                new BookServiceImpl(
                        new BookDAOImpl(
                                new DatabaseConnectorImpl())),
                new BookViewImpl());
        bookPresenter.start();
    }
}
