package me.cxis.junit;

import org.junit.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @BeforeClass
    public static void before() {
        System.out.println("Global beforeClass");
    }

    @AfterClass
    public static void after() {
        System.out.println("Global afterClass");
    }

    @Before
    public void setUp() {
        System.out.println("setUp...");
    }

    @After
    public void tearDown() {
        System.out.println("tearDown...");
    }

    @Test
    @Ignore
    public void testAdd() {
        int result = calculator.add(1, 2);
        Assert.assertEquals(30, result);
    }

    @Test
    public void testMinus() {
        int result = calculator.minus(5, 2);
        Assert.assertThat(result, greaterThan(2));
    }

    @Test
    public void testMultiply() {
        int result = calculator.multiply(4, 2);
        Assert.assertEquals(8, result);
    }

    @Test(timeout = 1000) // 单位为毫秒
    public void testSquareRoot() {
        calculator.squareRoot(4);
    }

    @Test(expected = Exception.class)
    public void testDivide() throws Exception {
        calculator.divide(4, 0);
    }


    @Test
    public void testEvaluate() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        assertEquals(6, sum);
    }
}
