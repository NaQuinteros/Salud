package test;

import java.text.DecimalFormat;

public class MarianoTests {

	public static void main(String[] args) {
		DecimalFormat df2 = new DecimalFormat(",##");
		Double a = 123.12341234;
		System.out.println(1+Double.parseDouble(df2.format(a)));
	}

}
