package com.vso.views;

import java.util.List;
import java.util.Scanner;

public class BookViewImpl implements BookView {

    private final Scanner scanner;

    public BookViewImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void show(String text) {
        System.out.println(text);
    }

    @Override
    public String getTextInput(String text) {
        show(text);
        return scanner.nextLine();
    }

    @Override
    public int getDecimalInput() {
        String numberInput = getTextInput("Input: ");
        while (!numberInput.matches("[0-9]+")) {
            numberInput = getTextInput("Invalid input, try again: ");
        }
        return Integer.parseInt(numberInput);
    }
    @Override
    public <T> void printList(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
    }

    @Override
    public void getOptions() {
        show("""
                Choose option: 
                1. Rent book
                2. Return book""");
    }
}
