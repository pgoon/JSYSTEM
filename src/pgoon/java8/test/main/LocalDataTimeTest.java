package pgoon.java8.test.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LocalDataTimeTest {

	public static void main(String[] args) {

		LocalDate ld = LocalDate.of(2012, 6, 16);
		LocalTime lt = LocalTime.of(15, 55, 2);
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		LocalDateTime ldt2 = LocalDateTime.now();
		LocalDateTime ldt3;

		System.out.println(ldt);
		System.out.println(ldt2);

		System.out.println(ChronoUnit.YEARS.between(ldt, ldt2) + "”N");
		System.out.println(ChronoUnit.MONTHS.between(ldt, ldt2) + "ŒŽ");
		System.out.println(ChronoUnit.DAYS.between(ldt, ldt2) + "“ú");

		ldt3 = ldt2.plusMonths(2)
			   .minusDays(10);

		System.out.println(ldt3);
		System.out.println(JapaneseDate.now());
		System.out.println(
				DateTimeFormatter.ofPattern("Gy”NMŒŽd“ú")
				.withChronology(JapaneseChronology.INSTANCE)
				.format(JapaneseDate.of(2016, 3, 21))
				);


	}

}
