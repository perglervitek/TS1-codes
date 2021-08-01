package cz.cvut.fel.ts1.shop;

import cz.cvut.fel.ts1.archive.PurchasesArchive;
import cz.cvut.fel.ts1.storage.NoItemInStorage;
import cz.cvut.fel.ts1.storage.Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class EShopControllerTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    @BeforeEach
    public void arrangeBefore(){
        System.setOut(new PrintStream(output));
    }
    @Test
    public void processBuyItemSuccess() {
        EShopController.startEShop();

        PurchasesArchive purchasesArchive = EShopController.getArchive();
        Storage eshopStorage = EShopController.getStorage();

        eshopStorage.printListOfStoredItems();
        purchasesArchive.printItemPurchaseStatistics();
        String expectedStorageOutput = "STORAGE IS CURRENTLY CONTAINING:" + System.lineSeparator() + "ITEM PURCHASE STATISTICS:";

        assertEquals(expectedStorageOutput, output.toString().trim());
        output.reset();

        // Add new cart
        ShoppingCart cart = EShopController.newCart();
        // There should be one cart present
        assertEquals(1, EShopController.getNumberOfCarts());


        final ShoppingCart finalCartEmpty = cart;
        // Try to order an empty cart
        assertDoesNotThrow(() ->
                EShopController.purchaseShoppingCart(finalCartEmpty, "First Last", "Praha")
        );
        output.reset();

        Item[] eshopItems = {
                new StandardItem(1, "Item1", 1F, "Cat1", 1),
                new StandardItem(2, "Item2", 10F, "Cat1", 5)
        };

        cart.addItem(eshopItems[0]);
        assertTrue(cart.getCartItems().contains(eshopItems[0]));
        output.reset();
        assertSame(eshopItems[0], cart.getCartItems().get(0));

        // Assert needs final
        final ShoppingCart finalCartFull = cart;
        // Try to order from empty storage
        assertThrows(NoItemInStorage.class, () -> EShopController.purchaseShoppingCart(finalCartFull, "First Last", "Street name"));

        // make the cart empty again
        cart.removeItem(eshopItems[0].getID());
        assertEquals(0, cart.getItemsCount());
        assertFalse(cart.getCartItems().contains(eshopItems[0]));
        output.reset();

        // Add new (different) item into cart
        cart.addItem(eshopItems[1]);
        assertEquals(1, cart.getItemsCount());
        assertTrue(cart.getCartItems().contains(eshopItems[1]));
        output.reset();

        // Same length as eshopItems
        int[] eshopItemsCount = {4,5};
        cart = EShopController.newCart();

        int index = 0;
        for(Item item : eshopItems) {
            eshopStorage.insertItems(item, eshopItemsCount[index]);
            cart.addItem(item);
            assertEquals(1 + index, cart.getItemsCount());
            assertTrue(cart.getCartItems().contains(eshopItems[index]));
            output.reset();
            index++;
        }

        try {
            EShopController.purchaseShoppingCart(cart, "First Last", "Street name");
            for (Item item : eshopItems) {
                assertEquals(1, purchasesArchive.getHowManyTimesHasBeenItemSold(item));
            }
        } catch (NoItemInStorage error) {
            error.printStackTrace();
        }

        try {
            EShopController.purchaseShoppingCart(cart, "First Last", "Street name");
            assertEquals(eshopItemsCount[0] - 2, purchasesArchive.getHowManyTimesHasBeenItemSold(eshopItems[0]));
        } catch (NoItemInStorage error) {
            error.printStackTrace();
        }
    }
}