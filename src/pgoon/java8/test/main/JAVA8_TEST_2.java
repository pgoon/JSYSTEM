package pgoon.java8.test.main;

import java.util.stream.Stream;

public class JAVA8_TEST_2 {

	public static void main(String[] args) {


		// STREAM
		String[] arrayStr = {"H","E","L","L","O","W","O","R","L","D"};

		Stream<String> streamStr = Stream.of(arrayStr);

		streamStr.forEach(str2 -> System.out.print(str2));

		//streamStr.filter(str3 -> str3.contains("W")).forEach(str3);
	}

}
