package Math;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FloorDivTest {

    // (int x, int y) - ordinary arguments
    @Test(dataProvider = "floorDivOrdinaryIntegers")
    public void floorDivOrdinaryTest(int x, int y) {
        double res = Math.floor(x / (y * 1.0));
        Assert.assertEquals(Math.floorDiv(x, y), (int) res);
    }

    // dataprovider for (int x, int y) - ordinary arguments
    @DataProvider(name = "floorDivOrdinaryIntegers")
    public Object[][] floorDivOrdinaryDataProvider() {
        return new Object[][]{
                {-4, 3}
        };
    }

    // (int x, int y) - arguments that cause integer overflow
    @Test(dataProvider = "floorDivOverflowIntegers")
    public void floorDivOverflowTest(int x, int y) {
        // integer overflow occurs
        // and the result is equal to the Integer.MIN_VALUE
        Assert.assertEquals(Math.floorDiv(x, y), Integer.MIN_VALUE);
    }

    // dataprovider for (int x, int y) - arguments that cause integer overflow
    @DataProvider(name = "floorDivOverflowIntegers")
    public Object[][] floorDivOverflowDataProvider() {
        return new Object[][]{
                {Integer.MIN_VALUE, -1}
        };
    }

    // int x, int y - arguments that cause ArithmeticException
    @Test(dataProvider = "floorDivArithmeticExIntegers")
    public void floorDivArithmeticExTest(int x, int y) {
        // if the divisor y is 0
        Assert.assertThrows(ArithmeticException.class, () -> Math.floorDiv(x, y));
    }

    // dataprovider for (int x, int y) - arguments that cause ArithmeticException
    @DataProvider(name = "floorDivArithmeticExIntegers")
    public Object[][] floorDivArithmeticExDataProvider() {
        return new Object[][]{
                {Integer.MIN_VALUE, 0}
        };
    }
}
