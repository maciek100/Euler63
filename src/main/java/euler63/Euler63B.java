package euler63;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

/**
 * Powerful digit counts
 *
 * Problem 63
 * The 5-digit number, 16807=7^5, is also a fifth power. 
 * Similarly, the 9-digit number, 134217728=8^9, is a ninth power.
 *
 * How many n-digit positive integers exist which are also an nth power?
 *
 * ANSWER = 49
 */
class Euler63B {
	
	private static int digitCount(BigInteger number) {
		return number.toString().length();
	}
	
	private static long raise(long base, long exp) {
		return (long)Math.pow((double)(base), (double)(exp));
	}

	long powerDigitCount() {
		long count = 0L;
		for(long num = 1; num < 2000; num++) {
			for(int exp = 1; exp < 100; exp++) {
				BigInteger powerBI = new BigInteger(Long.toString(num));
				powerBI = powerBI.pow(exp);
				if(exp == digitCount(powerBI)) {
					count++;
				}
			}
		}
		return count;
	}

	@Test
	public void testPowerDigitCount () {
		long count = powerDigitCount();
		assertEquals("", 49L, count);
	}
	
	@Test
	public void testRaising() {
		assertEquals("", 256L, raise(2L, 8L));
		assertEquals("", 16807L, raise(7L, 5L));
		assertEquals("", 134217728L, raise(8L, 9L));
	}
	
	@Test
	public void testDigitCount() {
		assertEquals("", 1, digitCount(BigInteger.ONE));
		assertEquals("", 2, digitCount(BigInteger.TEN));
		assertEquals("", 1, digitCount(new BigInteger("7")));
		assertEquals("", 2, digitCount(new BigInteger("10")));
		assertEquals("", 2, digitCount(new BigInteger("99")));
		assertEquals("", 11, digitCount(new BigInteger("58682746351")));
	}
}
