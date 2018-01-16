package ch.guggisberg.stefan.groupfitness.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;

public class Playground {

	public static void main(String[] args) {
		
		 LocalDate date = LocalDate.now();
System.out.println(date.withDayOfMonth(1));
date= date.withDayOfMonth(1);
System.out.println(date);

	}

}
