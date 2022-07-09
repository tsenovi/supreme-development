package org.vso.views;

import org.vso.viewmodels.CalculatorViewModel;

import java.util.Scanner;

public class CalculatorView {

    private final CalculatorViewModel viewModel;
    private final Scanner scanner;

    public CalculatorView() {
        viewModel = new CalculatorViewModel();
        scanner = new Scanner(System.in);

        setupLiveDataListeners();

        viewModel.onViewShown();
    }

    private void setupLiveDataListeners() {
        viewModel.getShouldShowInstructions().subscribe(data -> {
            if (data) printInstructions();
        });

        viewModel.getShouldGetInputFromUser().subscribe(data -> {
            if (data) acceptUserInput();
        });

        viewModel.getShouldGetNumbersForAddition().subscribe(data -> {
            if (data) acceptUserNumbersForAddition();
        });

        viewModel.getShouldGetNumbersForSubtraction().subscribe(data -> {
            if (data) acceptUserNumbersForSubtraction();
        });

        viewModel.getShouldGetNumbersForMultiplication().subscribe(data -> {
            if (data) acceptUserNumbersForMultiplication();
        });

        viewModel.getShouldGetNumbersForDivision().subscribe(data -> {
            if (data) acceptUserNumbersForDivision();
        });

        viewModel.getShouldTellIfNumberIsPositive().subscribe(data -> {
            if (data) acceptUserNumberForPositiveCheck();
        });

        viewModel.getResult().subscribe(this::showResult);

        viewModel.getPositiveResult().subscribe(this::showResult);
    }

    private void acceptUserNumberForPositiveCheck() {
        show("Enter number for Positive check:");
        viewModel.onUserInputForPositiveCheck(getUserDecimalInput());
    }

    private void acceptUserNumbersForDivision() {
        show("Enter numbers for Division:");
        viewModel.onUserInputForDivision(getUserDecimalInput(), getUserDecimalInput());
    }

    private void acceptUserNumbersForMultiplication() {
        show("Enter numbers for Multiplication:");
        viewModel.onUserInputForMultiplication(getUserDecimalInput(), getUserDecimalInput());
    }

    private void acceptUserNumbersForSubtraction() {
        show("Enter numbers for Subtraction:");
        viewModel.onUserInputForSubtraction(getUserDecimalInput(), getUserDecimalInput());
    }

    private void acceptUserNumbersForAddition() {
        show("Enter numbers for Addition:");
        viewModel.onUserInputForAddition(getUserDecimalInput(), getUserDecimalInput());
    }

    private <T> void showResult(T data) {
        show("Result:");
        show(data.toString());
    }

    private void acceptUserInput() {
        viewModel.onUserInputEntered(getUserDecimalInput());
    }

    private int getUserDecimalInput() {
        return scanner.nextInt();
    }

    private void show(String text) {
        System.out.println(text);
    }

    private void printInstructions() {
        show("""
                1. Addition
                2. Subtraction
                3. Multiplication
                4. Division
                5. Check for positive number""");
    }
}
