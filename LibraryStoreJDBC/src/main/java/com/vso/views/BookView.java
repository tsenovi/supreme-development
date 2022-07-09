package com.vso.views;

import java.util.List;

public interface BookView {
    void show(String text);

    String getTextInput(String text);

    int getDecimalInput();

    <T> void printList(List<T> list);

    void getOptions();
}
