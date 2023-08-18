import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexTestCalculator {

    static Calculator calculator;

    @BeforeAll
    public static void init() {
        calculator = new Calculator();
        System.out.println("Tests start");
    }

    @AfterAll
    public static void showTime() {
        System.out.println("Tests done");
    }

    //
    // Operations Tests
    //

    @ParameterizedTest
    @CsvSource({
            "2, 1, 2",
            "10, 5, 2",
            "-20, 4, -5",
            "10, -2, -5",
            "3, 2, 1.5",
            "2, 0, Infinity"
    })
    public void testDivideOperator(double a, double b, double expectedResult) {
        Operator op = new Divide();
        Double actionResult = op.go(a, b);
        assertEquals(expectedResult, actionResult);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 1, 1",
            "0, 10, -10",
            "-2, 10, -12",
            "-2, -2, 0"
    })
    public void testMinusOperator(double a, double b, double expectedResult) {
        Operator op = new Minus();
        Double actionResult = op.go(a, b);
        assertEquals(expectedResult, actionResult);

    }

    @ParameterizedTest
    @CsvSource({
            "0.5, 2, 1",
            "2, 2, 4",
            "3, 1, 3",
            "3, 0, 0"
    })
    public void testMultiplyOperator(double a, double b, double expectedResult) {
        Operator op = new Multiply();
        Double actionResult = op.go(a, b);
        assertEquals(expectedResult, actionResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0.5, 1, 1.5",
    })
    public void testPlusOperator(double a, double b, double expectedResult) {
        Operator op = new Plus();
        Double actionResult = op.go(a, b);
        assertEquals(expectedResult, actionResult);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 2, 4",
            "10, -2, 0.01",
            "-2, 2, 4",
            "-2, 3, -8",
    })
    public void testPowOperator(double a, double b, double expectedResult) {
        Operator op = new Pow();
        Double actionResult = op.go(a, b);
        assertEquals(expectedResult, actionResult);
    }


    //
    // Overall Tests
    //


    @Test
    public void testOperatorSelector() {
        String charOperator = "+";

        Operator actionResult = Operator.getOperator(charOperator);

        assertEquals(new Plus(), actionResult);
    }

    @ParameterizedTest
    @CsvSource({
        "10 + 9 * 1 - 15 / 2, 11.5",
            "2^2^2, 16.0"
    })
    public void testFinalSolving(String equation, String expectedResult) {
        String actionResult = calculator.solve(equation);
        assertEquals(expectedResult, actionResult);
    }
}
