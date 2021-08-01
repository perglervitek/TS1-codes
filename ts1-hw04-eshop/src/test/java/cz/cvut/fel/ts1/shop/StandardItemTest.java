package cz.cvut.fel.ts1.shop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.stream.Stream;

public class StandardItemTest {
    @Test
    public void constructorValidDataSuccess() {

        // Arrange
        int itemId = 1;
        String itemName = "New Standard item name";
        float itemPrice = 1.11F;
        String itemCategory = "New Standard item category";
        int itemLoyaltyPoints = 1;

        // Act
        StandardItem standardItem = new StandardItem(itemId, itemName, itemPrice, itemCategory, itemLoyaltyPoints);

        // Assert
        assertEquals(itemId, standardItem.getID());
        assertEquals(itemName, standardItem.getName());
        assertEquals(itemPrice, standardItem.getPrice());
        assertEquals(itemCategory, standardItem.getCategory());
        assertEquals(itemLoyaltyPoints, standardItem.getLoyaltyPoints());
    }

    @Test
    public void standardItemCopyIsNotSameButEqualsTrue() {
        // Arrange
        int itemId = 1;
        String itemName = "New Standard item name";
        float itemPrice = 1.11F;
        String itemCategory = "New Standard item category";
        int itemLoyaltyPoints = 1;
        StandardItem originalItem = new StandardItem(itemId, itemName, itemPrice, itemCategory, itemLoyaltyPoints);


        // Act
        StandardItem copiedItem = originalItem.copy();

        // Assert
        // Cvičení - Check same
        assertNotSame(originalItem, copiedItem);
        assertEquals(originalItem, copiedItem);
    }

    @ParameterizedTest
    @MethodSource("itemsCsvSource")
    public void itemOneEqualsItemTwo(Item itemOne, Item itemTwo, Boolean expectedResult) {
        assertEquals(itemOne.equals(itemTwo), expectedResult);
    }

    private static Stream < Arguments > itemsCsvSource() {
        return Stream.of(
                Arguments.of(
                        new StandardItem(1, "Equal item", 1F, "Equal category", 1),
                        new StandardItem(1, "Equal item", 1F, "Equal category", 1),
                        true
                ),
                Arguments.of(
                        new StandardItem(1, "Equal item", 1F, "Equal category", 1),
                        new StandardItem(2, "Equal item", 1F, "Equal category", 1),
                        false
                ),
                Arguments.of(
                        new StandardItem(1, "Equal item", 1F, "Equal category", 1),
                        new StandardItem(1, "Equal item different name", 1F, "Equal category", 1),
                        false
                ),
                Arguments.of(
                        new StandardItem(1, "Equal item", 1F, "Equal category", 1),
                        new StandardItem(1, "Equal item", 10F, "Equal category", 1),
                        false
                ),
                Arguments.of(
                        new StandardItem(1, "Equal item", 1F, "Equal category", 1),
                        new StandardItem(1, "Equal item", 1F, "Equal different category", 1),
                        false
                ),
                Arguments.of(
                        new StandardItem(1, "Equal item", 1F, "Equal category", 1),
                        new StandardItem(1, "Equal item", 1F, "Equal category", 10),
                        false
                )
        );
    }
}