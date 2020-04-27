import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MathTest {

    // Math.abs(int a) test
    @Test(dataProvider = "absIntegers")
    public void absTest(int notNegative, int negative, int minVal) {
        SoftAssert sa = new SoftAssert();

        // if the argument is not negative, the argument is returned
        sa.assertEquals(Math.abs(notNegative), notNegative);

        // if the argument is negative, the negation of the argument is returned
        sa.assertEquals(Math.abs(negative), -negative);

        // if the argument is equal to the value of Integer.MIN_VALUE
        // the result is that same negative value
        sa.assertEquals(Math.abs(minVal), minVal);

        sa.assertAll();
    }

    // dataprovider for Math.abs(int a) test
    @DataProvider(name = "absIntegers")
    public Object[][] absDataProvider() {
        return new Object[][]{
                {1, -2, Integer.MIN_VALUE}     // positive test
                //,{-1, 2, Integer.MIN_VALUE + 1}  // negative test
        };
    }

    // Math.addExact(int x, int y) test
    @Test(dataProvider = "addExactIntegers")
    public void addExactTest(int x, int y) {
        SoftAssert sa = new SoftAssert();

        try {
            // returns the sum of its arguments
            sa.assertEquals(Math.addExact(x, y), x + y);

        } catch (ArithmeticException ex) {
            // throws an exception if the result overflows an int
        } finally {
            sa.assertAll();
        }
    }

    // dataprovider for Math.addExact(int x, int y) test
    @DataProvider(name = "addExactIntegers")
    public Object[][] addExactDataProvider() {
        return new Object[][]{
                {1, -10},               // ordinary arguments
                {Integer.MAX_VALUE, 1}  // arguments that cause exception
        };
    }

    // Math.floorDiv(int x, int y) test
    @Test(dataProvider = "floorDivIntegers")
    public void floorDivTest(int x, int y) {
        SoftAssert sa = new SoftAssert();

        if (x == Integer.MIN_VALUE && y == -1)
            // integer overflow occurs
            // and the result is equal to the Integer.MIN_VALUE
            sa.assertEquals(Math.floorDiv(x, y), Integer.MIN_VALUE);
        else {
            double res = Math.floor(x / (y * 1.0));
            try {
                sa.assertEquals(Math.floorDiv(x, y), (int) res);
            } catch (ArithmeticException ex) {
                // if the divisor y is 0
            } finally {
                sa.assertAll();
            }
        }
    }

    // dataprovider for Math.floorDiv(int x, int y) test
    @DataProvider(name = "floorDivIntegers")
    public Object[][] floorDivDataProvider() {
        return new Object[][]{
                {-4, 3},                    // ordinary arguments
                {Integer.MIN_VALUE, -1},    // arguments that cause integer overflow
                {Integer.MIN_VALUE, 0}      // arguments that cause ArithmeticException
        };
    }
}
