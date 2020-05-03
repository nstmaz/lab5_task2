package Math;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class AddExactTest {

    Random r = new Random();
    int[] array = generateTwoIntegers();
    int[] overflowArray = generateTwoIntegersOverflow();

    // int x, int y - ordinary integers
    @Test(dataProvider = "addExactSumIntegers")
    public void addExactSumTest(int x, int y) {
        // returns the sum of its arguments
        Assert.assertEquals(Math.addExact(x, y), x + y);
    }

    // dataprovider for int x, int y - ordinary integers
    @DataProvider(name = "addExactSumIntegers")
    public Object[][] addExactSumDP() {
        return new Object[][]{
                {array[0], array[1]}
        };
    }

    // generates 2 integers, their sum doesn't overflow int type
    public int[] generateTwoIntegers() {
        int x1 = r.nextInt();   // any integer
        int x2;
        int min, max;
        int[] arr = new int[2]; // array to return

        if (x1<0) {
            min = Integer.MIN_VALUE-x1;
            max = Integer.MAX_VALUE;
            x2 = (r.nextInt(Integer.MAX_VALUE) + r.nextInt(2)) - r.nextInt(Math.abs(min));
        }
        else if (x1>0) {
            min = Integer.MIN_VALUE;
            max = Integer.MAX_VALUE - x1;
            x2 = (r.nextInt(max+1) - r.nextInt(Math.abs(min)));
        }
        else {
            x2 = r.nextInt();
        }

        arr[0] = x1;
        arr[1] = x2;
        return arr;
    }

    // (int x, int y) - arguments that cause exception
    @Test(dataProvider = "addExactExceptionIntegers")
    public void addExactExceptionTest(int x, int y) {
        // throws an exception if the result overflows an int
        Assert.assertThrows(ArithmeticException.class, () -> Math.addExact(x, y));
    }

    // dataprovider for (int x, int y)  - arguments that cause exception
    @DataProvider(name = "addExactExceptionIntegers")
    public Object[][] addExactExceptionDP() {
        return new Object[][]{
                {overflowArray[0], overflowArray[1]}
        };
    }

    // generates 2 integers, their sum overflows int type
    public int[] generateTwoIntegersOverflow() {
        int a = r.nextInt(2);   // min or max boundary overflow
        int[] arr = new int[2]; // array to return

        // min boundary overflow
        if (a==0) {
            arr[0] = Integer.MIN_VALUE;
            arr[1] = -1*(r.nextInt(Integer.MAX_VALUE)+1);
        }

        // max boundary overflow
        else { // a==1
            arr[0] = Integer.MAX_VALUE;
            arr[1] = r.nextInt(Integer.MAX_VALUE)+1;
        }

        return arr;
    }


}
