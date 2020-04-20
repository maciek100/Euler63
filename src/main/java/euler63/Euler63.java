package euler63;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
 * Defunc solution, use BigInteger ... makes it all much simpler, details in Euler63B.
 *
 * This sad class is just a first attempt, not only first BUT ALSO failed. :(
 */
class Euler63 {

	public static int powerDigitCount(long exponent) {
		int count = 0;
		for(long i = 0; i < 1000000000; i++) {

			long powered = power(i, exponent);
			if (powered != Long.MIN_VALUE && countPower(powered, exponent)) {
				System.out.println(i + "^" + exponent + " = " + powered);
				count++;
			}

		}
		System.out.println(count);
		return count;
	}		
	
	//@Test
//	public void testMe() {
//		int count = 0;
//		long overflowExample = Long.MAX_VALUE;
//		System.out.println("MAX LONG " + Long.MAX_VALUE);
//		 //System.out.println("Overflow: " + (overflowExample + 1));
//		 //System.out.println("Overflow: " + (overflowExample * 101010));
//
//		//long exp = 1L;
//		for(long exp = 0L; exp < 21; exp++) {
//			count += powerDigitCount(exp);
//		}
//		System.out.println("Global count = " + count);
//	}
	
	public static long power(long number, long power) {
		long result = number;
		for(long x = 1; x < power; x++) {
			result *= number;
			if(result < 0L) {
				//System.out.println("OVERFLOW " + number + "^" + x);
				//break;
				return Long.MIN_VALUE;
			}
		}
		return result;
	}
	
	@Test
	public void testPower() {
		assertEquals("", 125L, power(5L, 3L));
		assertEquals("", 1L, power(1L, 1L));
		assertEquals("", 4096L, power(2L, 12L));
		assertEquals("", 16807L, power(7L, 5L));
		assertEquals("", 134217728L, power(8L, 9L));
	}
	
	private static boolean countPower(long number, long power) {
		return (long) (Math.log10(number) + 1) == power;
	}
	
	private static int digitCount(long number) {
		return (int)(Math.log10(number) + 1);
	}
	
	private static long raise(long base, long exp) {
		return (long)Math.pow((double)(base), (double)(exp));
	}
	
	@Test
	public void testTest() {
		for(long num = 0; num < 4000; num++) {
			for(long exp = 0; exp < 100; exp++) {
				long power = raise(num, exp);
				if (power >= Long.MAX_VALUE || power < 0) {
					//System.out.println("Overflow " + num + "^" + exp);
					continue;
				}
				if(exp == digitCount(power)) {
					System.out.println(num + "^" + exp + " = " + power);
				}
			}
		}
		System.out.println("xxxx^yy = " + Long.MAX_VALUE);
		System.out.println("Over??? = " + (Long.MAX_VALUE * 333L)); //YES, it is INTENTIONAL!
	}
	
	@Test
	public void testRaising() {
		assertEquals("", 256L, raise(2L, 8L));
		assertEquals("", 16807L, raise(7L, 5L));
		assertEquals("", 134217728L, raise(8L, 9L));
	}
	
	@Test
	public void testDigitCount() {
		//assertEquals("", 1, digitCount(0L));
		assertEquals("", 1, digitCount(1L));
		assertEquals("", 1, digitCount(7L));
		assertEquals("", 2, digitCount(10L));
		assertEquals("", 2, digitCount(99L));
		assertEquals("", 11, digitCount(58682746351L));
	}
	
	@Test
	public void testPowers() {
		assertTrue("", countPower(3L * 3 * 3 * 3, 2L));
		assertTrue("", countPower(16807L, 5L));
		assertTrue("", countPower(134217728L, 9L));
	}
	
	//@Test
	public void test5s() {
//		for(long i = 10000; i <= 99999; i++) {
//			if(isNRootSimple(i, 5L)) {
//				System.out.println("MATCH " + i);
//			}
//			
//		}
		int count = 0;
		for (long i = 10; i < 20; i++) {
			count += countNdigiters(10,i);
			System.out.println(count);
		}
	}
	
	private static int countNdigiters(long base, long exp) {
		int count = 0;
		long start = (long)Math.pow(10, exp-1);
		//long stop = (long)Math.pow(10, exp);
		for(long i = start; i < start * 10; i++) {
			if(isNRootSimple(i, exp)) {
				System.out.println("MATCH " + i);
				count++;
			}
		}
		return count;
	}
	
	private static boolean isNRootSimple(long base, long exp) {
		double dres = Math.pow(base, 1.0 / exp);
		double ires = Math.round(dres);
		double diff = Math.abs(dres - ires);
		double limit =  Math.ulp(100000.0);
		if (diff < limit) {
		    return true;
		}
		return false;
	}
}
