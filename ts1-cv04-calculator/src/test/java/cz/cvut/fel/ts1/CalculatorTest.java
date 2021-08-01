package cz.cvut.fel.ts1;

import cz.cvut.fel.ts1.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    @Test
    public void add_10plus5_returns15(){
        // Arrange
        int a = 10;
        int b = 5;
        int expectedResult = 15;
        Calculator calc = new Calculator();
        // Act
        int result = calc.add(a,b);
        // Assert
        assertEquals(expectedResult, result);
    }
    @Test
    public void substract_10minus5_returns5(){
        // Arrange
        int a = 10;
        int b = 5;
        int expectedResult = 5;
        Calculator calc = new Calculator();
        //Act
        int result = calc.subtract(a,b);
        // Assert
        assertEquals(expectedResult, result);
    }
    @Test
    public void multiply_10multiply5_returns50(){
        // Arrange
        int a = 10;
        int b = 5;
        int expectedResult = 50;
        Calculator calc = new Calculator();
        // Act
        int result = calc.multiply(a,b);
        // Assert
        assertEquals(expectedResult, result);
    }
    @Test
    public void divide_10by2_returns5(){
        // Arrange
        int a = 10;
        int b = 2;
        int expectedResult = 5;
        Calculator calc = new Calculator();
        // Act
        int result = calc.divide(a,b);
        // Assert
        assertEquals(expectedResult, result);
    }
    @Test
    public void divide_by0_throwsException(){
        // Arrange
        int a = 10;
        int b = 0;
        Calculator calc = new Calculator();
        // Act & Assert
        assertThrows(ArithmeticException.class, () -> calc.divide(a,b));
    }

    @ParameterizedTest(name = "{0} plus {1} should be equal to {2}")
    @CsvSource({"1, 2, 3", "2, 3, 5"})
    public void add_addsAandB_returnsC(int a, int b, int c) {
// arrange
        Calculator calc = new Calculator();
        int expectedResult = c;
// act
        int result = calc.add(a, b);
// assert
        assertEquals(expectedResult, result);
    }


}
