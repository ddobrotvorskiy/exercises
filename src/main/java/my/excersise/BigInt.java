package my.excersise;

import java.util.*;

public class BigInt {
	
	private static final int DIGIT_MAX_BITS = 15;
	private static final int DIGIT_MAX_VALUE = 2 << DIGIT_MAX_BITS;
	private static final int DIGIT_10_MAX_LENGTH = String.valueOf(DIGIT_MAX_VALUE).length() - 1;
	private final int[] digits; 

	public BigInt(String number) {
		int x = number.length() / DIGIT_10_MAX_LENGTH;
		int size = nearestPow2(x);
		digits = new int[size];

		int endIndex = number.length();
		int idx = 0;
		do {
			int beginIndex = endIndex > DIGIT_10_MAX_LENGTH ? (endIndex - DIGIT_10_MAX_LENGTH) : 0;
			digits[ size - (++idx) ] = Integer.parseInt( number.substring(beginIndex, endIndex));
			endIndex -= DIGIT_10_MAX_LENGTH;
		} while (endIndex > 0);
	}
	private BigInt(int[] digits) {
		this.digits = digits;
	}

	static int nearestPow2(int x) {
		int res = Integer.highestOneBit(x);
		res = x > res ? res << 1 : res;
    	return res;
    	// return 2 << ( (int) Math.floor( (Math.log(x) / Math.log(2)))  );
	}

	public BigInt add(BigInt b) {
		int radix = (int) Math.pow(10, DIGIT_10_MAX_LENGTH);

		int[] buff = new int[ Math.max(b.digits.length, digits.length) ];
		int addition = 0;
		for (int i = 0; i < buff.length; i++) {
			int x = i >= digits.length ? 0 : digits[digits.length - i - 1];
			int y = i >= b.digits.length ? 0 : b.digits[b.digits.length - i - 1];
			int sum = addition + x + y;
			buff[buff.length - i - 1] = sum % radix;
			addition = sum / radix;
		}
		int[] newDigits;
		if (addition != 0) {
			newDigits = new int[buff.length << 1];
			newDigits[buff.length - 1] = addition;
			System.arraycopy(buff, 0, newDigits, buff.length, buff.length );
		} else {
			newDigits = buff;
		}
		return new BigInt(newDigits);
	}

	public String toDebugString() {
		return "length=" + digits.length + "\ndigits=" + Arrays.toString(digits);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean skipLeadingZeroes = true;
		for (int i=0; i<digits.length; i++) {
			if (digits[i] == 0 && skipLeadingZeroes ) continue;

			String digit = String.valueOf(digits[i]);
			if (!skipLeadingZeroes) {
				for (int j = 0; j< DIGIT_10_MAX_LENGTH - digit.length() ; j++)
					sb.append('0');				
			}
			sb.append(digit);
			skipLeadingZeroes = false;
		}
		if (sb.length() == 0)
			sb.append("0");
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof BigInt) ) return false;

		BigInt b = (BigInt) o;
		return Arrays.equals(digits, b.digits);
	}
}