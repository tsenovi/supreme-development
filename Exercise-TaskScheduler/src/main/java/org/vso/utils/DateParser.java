package org.vso.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {

    private static DateParser instance;

    private DateParser() {
    }

    public static DateParser getInstance() {
        if (instance == null) {
            instance = new DateParser();
        }
        return instance;
    }

    public static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public LocalDate parse(String date) {
        return LocalDate.parse(date, formatter);
    }

    public boolean isCorrectDate(String date) {
        return isCorrectInput(date);
    }

    private boolean isCorrectInput(String data) {
        LocalDate localDate = LocalDate.parse(data, formatter);
        return localDate != null;
    }
}
