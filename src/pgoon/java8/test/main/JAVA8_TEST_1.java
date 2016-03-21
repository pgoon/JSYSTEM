package pgoon.java8.test.main;

import pgoon.java8.test.inter.FUNC_INTERFACE_TEST;

public class JAVA8_TEST_1  {

	public static void main(String[] args) {

		// Šù‘¶
//		JAVA8_TEST_1 t = new JAVA8_TEST_1();
//		System.out.println(t.getSum(2));

		// ƒ‰ƒ€ƒ_Ž®
		FUNC_INTERFACE_TEST t3 = no -> {
										System.out.println(no * 2);
										return no * 3; };

		System.out.println(t3.getSum(100));
	}

}
