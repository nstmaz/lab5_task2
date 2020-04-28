package Math;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AbsTest {

    // 'a' - not negative int
    @Test(dataProvider = "absNotNegativeInt")
    public void absTestNotNegativeInt(int a) {
        // if the argument is not negative, the argument is returned
        Assert.assertEquals(Math.abs(a), a);
    }

    // dataprovider for 'a' - not negative int
    @DataProvider(name = "absNotNegativeInt")
    public Object[][] absNotNegativeDataProvider() {
        return new Object[][]{
                {1}
        };
    }

    // 'a' - negative int
    @Test(dataProvider = "absNegativeInt")
    public void absTestNegativeInt(int a) {
        // if the argument is negative, the negation of the argument is returned
        Assert.assertEquals(Math.abs(a), -a);
    }

    // dataprovider for 'a' - negative int
    @DataProvider(name = "absNegativeInt")
    public Object[][] absNegativeDataProvider() {
        return new Object[][]{
                {-1}
        };
    }

    // 'a' - Integer.MIN_VALUE
    @Test(dataProvider = "absMinValInt")
    public void absTestMinValInt(int a) {
        // if the argument is equal to the value of Integer.MIN_VALUE
        // the result is that same negative value
        Assert.assertEquals(Math.abs(a), a);
    }

    // dataprovider for 'a' - Integer.MIN_VALUE
    @DataProvider(name = "absMinValInt")
    public Object[][] absMinValDataProvider() {
        return new Object[][]{
                {Integer.MIN_VALUE}
        };
    }
}
