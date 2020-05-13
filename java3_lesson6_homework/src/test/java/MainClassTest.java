import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)

public class MainClassTest {
    int[] expected;
    int[] actuals;

    public MainClassTest(int[] expected, int[] actuals) {
        this.expected = expected;
        this.actuals = actuals;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                        {new int[]{1, 7}, new int[]{1, 4, 4, 2, 3, 4, 1, 7}},
                        {new int[]{7}, new int[]{1, 2, 3, 2, 3, 5, 4, 7}},
                        {new int[]{}, new int[]{3, 3, 3}},
                        {new int[]{3, 3}, new int[]{-4, 0, 4, 3, 3}},
                        {new int[]{}, new int[]{5, 4, 3, 3, 4}},
                        {new int[]{1, 3, 6, 100500}, new int[]{4, 1, 3, 6, 100500}},
                }
        );
    }

    static MainClass mainclass;

    @BeforeClass
    public static void init() {
        mainclass = new MainClass();
    }

    @Test()
    public void newMethod1ShouldHave4InArray() {
        Assert.assertArrayEquals(expected, mainclass.method1(actuals));
    }


}

