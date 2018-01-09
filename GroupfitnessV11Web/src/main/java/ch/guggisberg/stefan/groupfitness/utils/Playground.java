package ch.guggisberg.stefan.groupfitness.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;

public class Playground {

	public static void main(String[] args) {
		 Month month = Month.valueOf("March".toUpperCase());

	        System.out.printf("For the month of %s:%n", month);
	        LocalDate date = Year.now().atMonth(month).atDay(1).
	              with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
	        Month mi = date.getMonth();
	        while (mi == month) {
	            System.out.printf("%s%n", date);
	            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
	            mi = date.getMonth();
	        }


	}

}
