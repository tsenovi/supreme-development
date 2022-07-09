package org.vso.viewmodels;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorViewModelTest {

    private CalculatorViewModel viewModel;

    @BeforeEach
    void initCart(){
        viewModel = new CalculatorViewModel();
    }

    @AfterEach
    void clearCart(){
        viewModel = null;
    }

    @Test
    public void testOnUserInputEnteredWhenUserSelectsAdditionThenGetsNumbersForAddition() {
        viewModel.onUserInputEntered(1);

        boolean actual = viewModel.getShouldGetNumbersForAddition().getData();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputEnteredWhenUserSelectsSubtractionThenGetsNumbersForSubtraction() {
        viewModel.onUserInputEntered(2);

        boolean actual = viewModel.getShouldGetNumbersForSubtraction().getData();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputEnteredWhenUserSelectsMultiplicationThenGetsNumbersForMultiplication() {
        viewModel.onUserInputEntered(3);

        boolean actual = viewModel.getShouldGetNumbersForMultiplication().getData();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputEnteredWhenUserSelectsDivisionThenGetsNumbersForDivision() {
        viewModel.onUserInputEntered(4);

        boolean actual = viewModel.getShouldGetNumbersForDivision().getData();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputEnteredWhenUserSelectsPositiveCheckThenGetsNumberForPositiveCheck() {
        viewModel.onUserInputEntered(5);

        boolean actual = viewModel.getShouldTellIfNumberIsEven().getData();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputForAdditionWhenOnePlusOneThenGetsTwo() {
        int numberOne = 1;
        int numberTwo = 1;
        viewModel.onUserInputForAddition(numberOne, numberTwo);
        Integer actual = viewModel.getResult().getData();
        Integer expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputForAdditionWhenThreePlusSevenThenGetsTen() {
        int numberOne = 3;
        int numberTwo = 7;
        viewModel.onUserInputForAddition(numberOne, numberTwo);
        Integer actual = viewModel.getResult().getData();
        Integer expected = 10;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputForSubtractionWhenOneMinusOneThenGetsZero() {
        int numberOne = 1;
        int numberTwo = 1;
        viewModel.onUserInputForSubtraction(numberOne, numberTwo);
        Integer actual = viewModel.getResult().getData();
        Integer expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputForSubtractionWhenTenMinusThreeThenGetsSeven() {
        int numberOne = 10;
        int numberTwo = 3;
        viewModel.onUserInputForSubtraction(numberOne, numberTwo);
        Integer actual = viewModel.getResult().getData();
        Integer expected = 7;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputForMultiplicationWhenTwoMultiplyByTwoThenGetsFour() {
        int numberOne = 2;
        int numberTwo = 2;
        viewModel.onUserInputForMultiplication(numberOne, numberTwo);
        Integer actual = viewModel.getResult().getData();
        Integer expected = 4;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputForSubtractionWhenTwoMultiplyByFourThenGetsEight() {
        int numberOne = 2;
        int numberTwo = 4;
        viewModel.onUserInputForMultiplication(numberOne, numberTwo);
        Integer actual = viewModel.getResult().getData();
        Integer expected = 8;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputForDivisionWhenTwoDividedByTwoThenGetsOne() {
        int numberOne = 2;
        int numberTwo = 2;
        viewModel.onUserInputForDivision(numberOne, numberTwo);
        Integer actual = viewModel.getResult().getData();
        Integer expected = 1;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputForDivisionWhenNineDividedByThreeThenGetsThree() {
        int numberOne = 9;
        int numberTwo = 3;
        viewModel.onUserInputForDivision(numberOne, numberTwo);
        Integer actual = viewModel.getResult().getData();
        Integer expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputForEvenCheckWhenUserInputIsEvenNumberThenGetsTrue() {
        int number = 10;
        viewModel.onUserInputForEvenCheck(number);
        boolean actual = viewModel.getIsEven().getData();
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void testOnUserInputForEvenCheckWhenUserInputIsOddNumberThenGetsFalse() {
        int number = -9;
        viewModel.onUserInputForEvenCheck(number);
        boolean actual = viewModel.getIsEven().getData();
        boolean expected = false;

        assertEquals(expected, actual);
    }
}
