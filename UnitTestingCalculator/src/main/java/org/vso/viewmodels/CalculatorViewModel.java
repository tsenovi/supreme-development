package org.vso.viewmodels;

import org.vso.livedata.LiveData;

public class CalculatorViewModel {

    private LiveData<Boolean> shouldShowInstructions;

    private LiveData<Boolean> shouldGetInputFromUser;

    private LiveData<Boolean> shouldGetNumbersForAddition;

    private LiveData<Boolean> shouldGetNumbersForSubtraction;

    private LiveData<Boolean> shouldGetNumbersForMultiplication;

    private LiveData<Boolean> shouldGetNumbersForDivision;

    private LiveData<Boolean> shouldTellIfNumberIsEven;

    private LiveData<Boolean> isEven;

    private LiveData<Integer> result;

    public CalculatorViewModel() {
        this.shouldShowInstructions = new LiveData<>(false);
        this.shouldGetInputFromUser = new LiveData<>(false);
        this.shouldGetNumbersForAddition = new LiveData<>(false);
        this.shouldGetNumbersForSubtraction = new LiveData<>(false);
        this.shouldGetNumbersForMultiplication = new LiveData<>(false);
        this.shouldGetNumbersForDivision = new LiveData<>(false);
        this.shouldTellIfNumberIsEven = new LiveData<>(false);
        this.isEven = new LiveData<>(false);
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


    public LiveData<Boolean> getShouldTellIfNumberIsEven() {
        return shouldTellIfNumberIsEven;
    }

    public LiveData<Integer> getResult() {
        return result;
    }

    public LiveData<Boolean> getIsEven() {
        return isEven;
    }

    public void onUserInputEntered(int userInput) {
        switch (userInput) {
            case 1 -> setupAddition();
            case 2 -> setupSubtraction();
            case 3 -> setupMultiplication();
            case 4 -> setupDeletion();
            case 5 -> setupCheckForEvenNumber();
        }
    }

    private void setupCheckForEvenNumber() {
        shouldTellIfNumberIsEven.post(true);
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

    public void onUserInputForEvenCheck(int number) {
        if (number % 2 == 0) isEven.post(true);
        else isEven.post(false);
    }
}
