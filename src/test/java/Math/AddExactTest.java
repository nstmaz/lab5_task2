package Math;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddExactTest {


    // int x, int y - ordinary integers
    @Test(dataProvider = "addExactSumIntegers")
    public void addExactSumTest(int x, int y) {
        // returns the sum of its arguments
        Assert.assertEquals(Math.addExact(x, y), x + y);
    }

    // dataprovider for (int x, int y) - ordinary integers
    @DataProvider(name = "addExactSumIntegers")
    public Object[][] addExactSumDataProvider() {
        return new Object[][]{
                {1, -10}
        };
    }

    // (int x, int y) - arguments that cause exception
    @Test(dataProvider = "addExactExceptionIntegers")
    public void addExactExceptionTest(int x, int y) {
        // throws an exception if the result overflows an int
        Assert.assertThrows(ArithmeticException.class, () -> Math.addExact(x, y));
    }

    // dataprovider for (int x, int y)  - arguments that cause exception
    @DataProvider(name = "addExactExceptionIntegers")
    public Object[][] addExactExceptionDataProvider() {
        return new Object[][]{
                {Integer.MAX_VALUE, 1}
        };
    }
}
