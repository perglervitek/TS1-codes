package cz.cvut.fel.ts1.archive;

import cz.cvut.fel.ts1.shop.Item;
import cz.cvut.fel.ts1.shop.Order;
import cz.cvut.fel.ts1.shop.ShoppingCart;
import cz.cvut.fel.ts1.shop.StandardItem;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyInt;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;



class PurchasesArchiveTest {
    private ByteArrayOutputStream outputStream;
    private PurchasesArchive purchasesArchive;
    private HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive = new HashMap<>();

    @Mock
    private ArrayList<Order> orderArchiveMock;
    @Mock
    ItemPurchaseArchiveEntry itemPurchaseArchiveEntryMock;
    @Mock
    StandardItem itemMock;
    @BeforeEach
    public void arrangeBefore() {
        MockitoAnnotations.openMocks(this);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        when(itemPurchaseArchiveEntryMock.getCountHowManyTimesHasBeenSold()).thenReturn(1);
        when(itemPurchaseArchiveEntryMock.toString()).thenReturn("ITEM  Item   ID 0   NAME Standard Item   CATEGORY Category   HAS BEEN SOLD 1 TIMES");

        itemPurchaseArchive.put(0, itemPurchaseArchiveEntryMock);
        purchasesArchive = new PurchasesArchive(itemPurchaseArchive, orderArchiveMock);
    }

    @Test
    void getItemStatisticsForEmptyArchive() {
        itemPurchaseArchive = new HashMap<>();
        new PurchasesArchive(itemPurchaseArchive, orderArchiveMock).printItemPurchaseStatistics();

        assertEquals("ITEM PURCHASE STATISTICS:", outputStream.toString().trim());
        outputStream.reset();
    }

    @Test
    void getItemStatisticsForOneItemSuccess() {
        Item item = new StandardItem(1, "Standard Item", 1F, "Category name", 10);
        ItemPurchaseArchiveEntry itemArchive = new ItemPurchaseArchiveEntry(item);
        HashMap<Integer, ItemPurchaseArchiveEntry> map = new HashMap<>();
        map.put(0, itemArchive);
        PurchasesArchive purchasesArchive = new PurchasesArchive(map, null);

        purchasesArchive.printItemPurchaseStatistics();

        Assertions.assertEquals("ITEM PURCHASE STATISTICS:"+System.lineSeparator()+ itemArchive.toString() + System.lineSeparator(), outputStream.toString());
        outputStream.reset();
    }

    @Test
    void getSoldItemNumberReturnsOne() {
        when(itemMock.getID()).thenReturn(0);

        assertEquals(1, purchasesArchive.getHowManyTimesHasBeenItemSold(itemMock));
    }

    @Test
    void getHowManyTimesHasBeenItemSoldNotInArchive_Zero() {
        when(itemMock.getID()).thenReturn(1);

        assertEquals(0, purchasesArchive.getHowManyTimesHasBeenItemSold(itemMock));
    }

    @Test
    void addOrderToArchiveFirstItemOccuranceCreatesNewArchive() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(itemMock);
        when(itemMock.getID()).thenReturn(1);
        ShoppingCart cart = new ShoppingCart(items);
        Order order = new Order(cart, "First Last", "Prague");

        try (MockedConstruction mocked = mockConstruction(ItemPurchaseArchiveEntry.class)) {
            purchasesArchive.putOrderToPurchasesArchive(order);
            assertEquals(1, mocked.constructed().size());
        }
    }

    @Test
    void addOrderToArchiveWithItemAlreadyAddedInArchiveIncreasesSoldNumber() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(itemMock);
        when(itemMock.getID()).thenReturn(0);
        ShoppingCart cart = new ShoppingCart(items);
        Order order = new Order(cart, "First Last", "Prague");

        purchasesArchive.putOrderToPurchasesArchive(order);

        verify(itemPurchaseArchiveEntryMock, times(1)).increaseCountHowManyTimesHasBeenSold(anyInt());
    }
}