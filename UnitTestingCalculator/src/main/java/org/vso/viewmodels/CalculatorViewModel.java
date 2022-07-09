package org.vso.viewmodels;

import org.vso.livedata.LiveData;

public class CalculatorViewModel {

    private LiveData<Boolean> shouldShowInstructions;

    private LiveData<Boolean> shouldGetInputFromUser;

    private LiveData<Boolean> shouldGetNumbersForAddition;

    private LiveData<Boolean> shouldGetNumbersForSubtraction;

    private LiveData<Boolean> shouldGetNumbersForMultiplication;

    private LiveData<Boolean> shouldGetNumbersForDivision;

    private LiveData<Boolean> shouldTellIfNumberIsPositive;

    private LiveData<Boolean> positiveResult;

    private LiveData<Integer> result;

    public CalculatorViewModel() {
        this.shouldShowInstructions = new LiveData<>(false);
        this.shouldGetInputFromUser = new LiveData<>(false);
        this.shouldGetNumbersForAddition = new LiveData<>(false);
        this.shouldGetNumbersForSubtraction = new LiveData<>(false);
        this.shouldGetNumbersForMultiplication = new LiveData<>(false);
        this.shouldGetNumbersForDivision = new LiveData<>(false);
        this.shouldTellIfNumberIsPositive = new LiveData<>(false);
        this.positiveResult = new LiveData<>(false);
        this.result = new LiveData<>(null);
    }

    public void onViewShown() {
        shouldShowInstructions.post(true);
        shouldGetInputFromUser.post(true);
    }

    public LiveData<Boolean> getShouldShowInstructions() {
        return shouldShowInstructions;
    }

    public LiveData<Boolean> getShouldGetInputFromUser() {
        return shouldGetInputFromUser;
    }

    public LiveData<Boolean> getShouldGetNumbersForAddition() {
        return shouldGetNumbersForAddition;
    }

    public LiveData<Boolean> getShouldGetNumbersForSubtraction() {
        return shouldGetNumbersForSubtraction;
    }

    public LiveData<Boolean> getShouldGetNumbersForMultiplication() {
        return shouldGetNumbersForMultiplication;
    }

    public LiveData<Boolean> getShouldGetNumbersForDivision() {
        return shouldGetNumbersForDivision;
    }


    public LiveData<Boolean> getShouldTellIfNumberIsPositive() {
        return shouldTellIfNumberIsPositive;
    }

    public LiveData<Integer> getResult() {
        return result;
    }

    public LiveData<Boolean> getPositiveResult() {
        return positiveResult;
    }

    public void onUserInputEntered(int userInput) {
        switch (userInput) {
            case 1 -> setupAddition();
            case 2 -> setupSubtraction();
            case 3 -> setupMultiplication();
            case 4 -> setupDeletion();
            case 5 -> setupCheckForPositiveNumber();
        }
    }

    private void setupCheckForPositiveNumber() {
        shouldTellIfNumberIsPositive.post(true);
    }

    private void setupDeletion() {
        shouldGetNumbersForDivision.post(true);
    }

    private void setupMultiplication() {
        shouldGetNumbersForMultiplication.post(true);
    }

    private void setupSubtraction() {
        shouldGetNumbersForSubtraction.post(true);
    }

    private void setupAddition() {
        shouldGetNumbersForAddition.post(true);
    }

    public void onUserInputForAddition(int numberOne, int numberTwo) {
        result.post(numberOne + numberTwo);
    }

    public void onUserInputForSubtraction(int numberOne, int numberTwo) {
        result.post(numberOne - numberTwo);
    }

    public void onUserInputForMultiplication(int numberOne, int numberTwo) {
        result.post(numberOne * numberTwo);
    }

    public void onUserInputForDivision(int numberOne, int numberTwo) {
        result.post(numberOne / numberTwo);
    }

    public void onUserInputForPositiveCheck(int number) {
        if (number >= 0) positiveResult.post(true);
        else positiveResult.post(false);
    }
}
