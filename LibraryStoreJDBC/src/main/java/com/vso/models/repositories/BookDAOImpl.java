package com.vso.models.repositories;

import com.vso.models.utils.DatabaseConnector;
import com.vso.models.dto.BookDTO;
import com.vso.models.entities.Book;
import com.vso.models.entities.Status;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private final DatabaseConnector connector;

    public BookDAOImpl(DatabaseConnector databaseConnector) {
        this.connector = databaseConnector;
        createTableBooks();
//        insertBooks();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connector.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books ORDER BY id ASC");) {
            while (resultSet.next()) {
                Book book = new Book(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> getBooksByName(BookDTO bookDTO) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connector.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books " +
                     "WHERE name LIKE '%" + bookDTO.getName() + "%' ORDER BY id ASC;")) {
            while (resultSet.next()) {
                Book book = new Book(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBookByName(BookDTO bookDTO) {
        Book book = null;
        try (Connection connection = connector.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books " +
                     "WHERE name='" + bookDTO.getName() + "';")) {
            while (resultSet.next()) {
                book = new Book(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void updateBook(Book book) {
        try (Connection connection = connector.connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE Books SET status='" + book.getStatus().name() + "' WHERE id=" + book.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTableBooks() {
        try (Connection connection = connector.connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
                    "Books (id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                    "name varchar(40), " +
                    "status varchar(40));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertBooks() {
        try (Connection connection = connector.connect();
             Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO books(name, status) VALUES ('The Mist 1', '" + Status.AVAILABLE.name() + "')");
            statement.execute("INSERT INTO books(name, status) VALUES ('The Mist 2', '" + Status.AVAILABLE.name() + "')");
            statement.execute("INSERT INTO books(name, status) VALUES ('The Mist 3', '" + Status.AVAILABLE.name() + "')");
            statement.execute("INSERT INTO books(name, status) VALUES ('The Mist 4', '" + Status.AVAILABLE.name() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
