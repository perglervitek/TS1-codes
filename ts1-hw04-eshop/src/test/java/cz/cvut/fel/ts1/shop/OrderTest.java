package cz.cvut.fel.ts1.shop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class OrderTest {
    @Test
    public void constructorNullValuesThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Order(null, null, null));
    }

    @Test
    public void constructorValidDataEmptyCartSuccess() {

        // Arrange
        ShoppingCart shoppingCart = new ShoppingCart();
        String orderCustomerName = "First Last";
        String orderCustomerAddress = "Praha 110 00, Novy Svet 11";
        int expectedOrderState = 0;

        // Act
        Order order = new Order(shoppingCart, orderCustomerName, orderCustomerAddress);

        // Assert
        assertEquals(shoppingCart.getCartItems(), order.getItems());
        assertEquals(orderCustomerName, order.getCustomerName());
        assertEquals(orderCustomerAddress, order.getCustomerAddress());
        assertEquals(expectedOrderState, order.getState());
    }

    @Test
    public void constructorValidDataItemAlreadyAddedInCartSuccess(){
        ArrayList<Item> cartItems = new ArrayList<>();
        String orderCustomerName = "First Last";
        String orderCustomerAddress = "Praha 110 00, Novy Svet 11";
        int expectedOrderState = 0;

        Item eshopItem = new StandardItem(1, "Item name", 1.1F, "Category name", 1 );
        cartItems.add(eshopItem);
        ShoppingCart cart = new ShoppingCart(cartItems);
        Order order = new Order(cart, orderCustomerName, orderCustomerAddress);

        assertEquals(cart.getCartItems(), order.getItems());
        assertEquals(orderCustomerName, order.getCustomerName());
        assertEquals(orderCustomerAddress, order.getCustomerAddress());
        assertEquals(expectedOrderState, order.getState());
    }

    @Test
    public void validStateConstructorEmptyCartSuccess() {
        ShoppingCart cart = new ShoppingCart();
        String orderCustomerName = "First Last";
        String orderCustomerAddress = "Praha 110 00, Novy Svet 11";
        int state = 1;

        Order order = new Order(cart, orderCustomerName, orderCustomerAddress, state);

        assertEquals(cart.getCartItems(), order.getItems());
        assertEquals(orderCustomerName, order.getCustomerName());
        assertEquals(orderCustomerAddress, order.getCustomerAddress());
        assertEquals(state, order.getState());
    }

    @Test
    public void validStateConstructorItemsInCartSuccess() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new StandardItem(1, "Item name", 1F, "Category name", 1));
        ShoppingCart cart = new ShoppingCart(items);
        String orderCustomerName = "First Last";
        String orderCustomerAddress = "Praha 110 00, Novy Svet 11";
        int state = 1;

        Order order = new Order(cart, orderCustomerName, orderCustomerAddress, state);

        assertEquals(cart.getCartItems(), order.getItems());
        assertEquals(orderCustomerName, order.getCustomerName());
        assertEquals(orderCustomerAddress, order.getCustomerAddress());
        assertEquals(state, order.getState());
    }

}
