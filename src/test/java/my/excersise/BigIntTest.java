package my.excersise;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.*;

public class BigIntTest {
	@DataProvider
	public Object[][] test_data() {
		return new Object[][] {
			new Object[] {"1"},
			new Object[] {"10"},
			new Object[] {"1000"},
			new Object[] {"10000000"},
			new Object[] {"123123123"},
			new Object[] {"1111111111222222222233333333334444444444555555555566666666667777777777888888888899999999990000000000"}
		};
	}

	@Test(dataProvider = "test_data")
	public void test_constructor(String number){

		System.out.println("number=" + number);
		BigInt b = new BigInt(number);
		System.out.println(b.toString());		
		System.out.println(b.toDebugString());
		System.out.println();

		assertEquals(number, b.toString());

	}

	@DataProvider
	public Object[][] test_add_data() {
		return new Object[][] {
			new Object[] {"0", "0", "0" },
			new Object[] {"0", "1", "1" },
			new Object[] {"0", "10000000", "10000000" },
			new Object[] {"1", "9", "10" },
			new Object[] {"100", "900", "1000" },
			new Object[] {"111111111111111111111111111111111111111111111111111111111111111111",
			              "222222222222222222222222222222222222222222222222222222222222222222",
			              "333333333333333333333333333333333333333333333333333333333333333333" }
		};
	}
	@Test(dataProvider = "test_add_data")
	public void test_add(String x, String y, String sum){

		BigInt a = new BigInt(x);
		BigInt b = new BigInt(y);
		BigInt s1 = a.add(b);
		BigInt s2 = b.add(a);

		
		assertEquals(s1, s2);
		assertEquals(s1.toString(), sum);
	}

	@DataProvider
	public Object[][] test_multiply_data() {
		return new Object[][] {
			new Object[] {"0", "0", "0" },
			new Object[] {"0", "1", "0" },
			new Object[] {"0", "100000000000", "0" },
			new Object[] {"1", "9", "9" },
			new Object[] {"100", "900", "90000" },
			new Object[] {"1000", "1000", "1000000"},
			new Object[] {"90000000", "20000000", "1800000000000000"},
			new Object[] {"1234", "5678", "7006652" }
		};
	}

	@Test(dataProvider = "test_multiply_data")
	public void test_multiply(String x, String y, String prod){

		BigInt a = new BigInt(x);
		BigInt b = new BigInt(y);
		BigInt s1 = a.multiply(b);
		BigInt s2 = b.multiply(a);

		
		assertEquals(s1, s2);
		assertEquals(s1.toString(), prod, "Expected " + x + " * " + y + " = " + prod);
	}

    @DataProvider
	public Object[][] test_nearestPow2_data() {
		return new Object[][] {
			new Object[] {0, 2},
			new Object[] {1, 2},
			new Object[] {2, 2},
			new Object[] {3, 4},
			new Object[] {4, 4},
			new Object[] {5, 8},
			new Object[] {8, 8},
			new Object[] {9, 16},
			new Object[] {16, 16},
			new Object[] {17, 32},
			new Object[] {Integer.MAX_VALUE, Integer.MIN_VALUE}
		};
	}
	@Test(dataProvider = "test_nearestPow2_data")
	public void test_nearestPow2(int x, int res) {
		assertEquals(BigInt.nearestPow2(x), res);
	}

}