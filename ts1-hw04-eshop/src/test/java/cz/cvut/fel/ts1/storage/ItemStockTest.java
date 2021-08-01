package cz.cvut.fel.ts1.storage;

import cz.cvut.fel.ts1.shop.StandardItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ItemStockTest {
    private StandardItem standardItem;
    private ItemStock itemStock;

    @BeforeEach
    public void arrangeBefore(){
        this.standardItem = new StandardItem(1, "Standard item", 1F, "Category name", 1);
        this.itemStock = new ItemStock(standardItem);
    }

    @Test
    public void constructorValidDataSuccess() {
        assertEquals(standardItem, itemStock.getItem());
    }

    @ParameterizedTest(name =  "Stock items 0 decremented by {1} should be {2}")
    @CsvSource({
            "2, -2",
            "5, -5"
    })
    void decreaseItemCount_fromAByB_returnsC(int a, int b) {
        itemStock.decreaseItemCount(a);

        assertEquals(b, itemStock.getCount());
    }

    @ParameterizedTest(name =  "Stock items 0 incremented by {1} should be {2}")
    @CsvSource({
            "2, 2",
            "5, 5",
    })
    void increaseItemCountfromNumAByNumBReturnsNumcC(int numA, int numB) {
        itemStock.IncreaseItemCount(numA);

        assertEquals(numB, itemStock.getCount());
    }
}