package cart;

import cart.item.Apple;
import cart.item.Chocolate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapShoppingCartTest {

    private MapShoppingCart mapShoppingCart;

    @BeforeEach
    void initCart(){
        mapShoppingCart = new MapShoppingCart();
    }

    @AfterEach
    void clearCart(){
        mapShoppingCart = null;
    }

    @Test
    void testGetUniqueItemsWhenAddingThreeApplesAndTwoChocolatesThenCartHasTwoItems() {
        Apple apple = new Apple("apple", "red", 1.5);
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(apple);

        Chocolate chocolate = new Chocolate("choco", "white", 2.5);
        mapShoppingCart.addItem(chocolate);
        mapShoppingCart.addItem(chocolate);

        int actualSize = mapShoppingCart.getUniqueItems().size();
        int expectedSize = 2;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void testGetUniqueItemsWhenAddingThreeApplesThenCartHasOneItems() {
        Apple apple = new Apple("apple", "red", 1.5);
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(apple);

        int actualSize = mapShoppingCart.getUniqueItems().size();
        int expectedSize = 1;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void testAddItemWhenAddingOneItemThenTheSizeOfCartIncreaseToOne() {
        Apple apple = new Apple("apple", "red", 1.5);
        mapShoppingCart.addItem(apple);

        double actual = mapShoppingCart.getUniqueItems().size();
        double expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void testAddItemWhenAddingNullItemThenTheSizeOfCartRemainsTheSame() {
        mapShoppingCart.addItem(null);

        double actual = mapShoppingCart.getUniqueItems().size();
        double expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void testRemoveItemWhenCartHasOneItemThenTheCartIsEmpty() {
        Apple apple = new Apple("apple", "red", 1.5);
        mapShoppingCart.addItem(apple);
        mapShoppingCart.removeItem(apple);

        double actual = mapShoppingCart.getUniqueItems().size();
        double expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void testRemoveItemWhenCartDoesNotHaveTheItemToRemoveThenCartIsUnchanged() {
        Apple apple = new Apple("apple", "red", 1.5);
        Chocolate chocolate = new Chocolate("choco", "brown", 2.5);
        mapShoppingCart.addItem(apple);
        mapShoppingCart.removeItem(chocolate);

        double actual = mapShoppingCart.getUniqueItems().size();
        double expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void testGetTotalPriceWhenCartHasThreeApplesByOneFiftyAndTwoChocoByTwoFiftyThenReturnNineFifty() {
        Apple apple = new Apple("apple", "red", 1.5);
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(apple);
        mapShoppingCart.addItem(apple);

        Chocolate chocolate = new Chocolate("choco", "white", 2.5);
        mapShoppingCart.addItem(chocolate);
        mapShoppingCart.addItem(chocolate);

        double actual = mapShoppingCart.getTotal();
        double expected = 9.5;
        assertEquals(expected, actual);
    }

    @Test
    void testGetTotalPriceWhenCartIsEmptyThenReturnZero() {
        double actual = mapShoppingCart.getTotal();
        double expected = 0;
        assertEquals(expected, actual);
    }
}