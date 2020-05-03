package Math;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class FloorDivTest {

    Random r = new Random();
    int[] array = generateOrdinaryArgs();

    // (int x, int y) - ordinary arguments
    @Test(dataProvider = "floorDivOrdinaryIntegers")
    public void floorDivOrdinaryTest(int x, int y) {
        double res = Math.floor(x / (y * 1.0));
        Assert.assertEquals(Math.floorDiv(x, y), (int) res);
    }

    // dataprovider for (int x, int y) - ordinary arguments
    @DataProvider(name = "floorDivOrdinaryIntegers")
    public Object[][] floorDivOrdinaryDP() {
        return new Object[][]{
                {array[0], array[1]}
        };
    }

    // generates ordinary integers without overflows or exceptions floorDivOrdinaryDP
    int[] generateOrdinaryArgs() {
        boolean stop = false;
        int x1 = 0, x2 = 0;
        int[] arr = new int[2]; // array to return

        while(!stop) {
            x1 = r.nextInt();
            x2 = r.nextInt();
            // (x1 == Integer.MIN_VALUE && x2 == -1) cause integer overflow
            // (x2 == 0) cause ArithmeticException
            if (!((x1 == Integer.MIN_VALUE && x2 == -1) || (x2 == 0)))
                stop = true;
        }

        arr[0] = x1;
        arr[1] = x2;

        return arr;
    }

    // (int x, int y) - arguments that cause integer overflow
    @Test(dataProvider = "floorDivOverflowIntegers")
    public void floorDivOverflowTest(int x, int y) {
        // if the dividend is the Integer.MIN_VALUE and the divisor is -1
        // integer overflow occurs
        // and the result is equal to the Integer.MIN_VALUE
        Assert.assertEquals(Math.floorDiv(x, y), Integer.MIN_VALUE);
    }

    // dataprovider for (int x, int y) - arguments that cause integer overflow
    @DataProvider(name = "floorDivOverflowIntegers")
    public Object[][] floorDivOverflowDP() {
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
    public Object[][] floorDivArithmeticExDP() {
        return new Object[][]{
                {r.nextInt(), 0}
        };
    }
}
