package com.vso.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {

    private final int id;
    private final String name;
    private Status status;

    public Book(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("name");
        this.status = Status.valueOf(resultSet.getString("status"));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
