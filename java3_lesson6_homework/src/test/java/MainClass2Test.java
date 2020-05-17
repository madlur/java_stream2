import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;


    @RunWith(Parameterized.class)

    public class MainClass2Test {
        boolean expected;
        int[] actuals;
        static MainClass2 mainclass2;

        public MainClass2Test(boolean expected, int[] actuals) {
            this.expected = expected;
            this.actuals = actuals;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                            {true, new int[]{1, 1, 1, 4, 4, 1, 4, 4}},
                            {false, new int[]{1, 1, 1, 1, 1, 1}},
                            {false, new int[]{4, 4, 4,4}},
                            {false, new int[]{1, 4, 4, 1, 1,4,3}},
                    }
            );
        }

        @BeforeClass
        public static void init() {
            mainclass2 = new MainClass2();
        }

        @Test()
        public void newMethod1ShouldHave4InArray() {
            Assert.assertEquals(expected, mainclass2.method2fast(actuals));
        }


    }