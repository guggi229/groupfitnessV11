package ch.guggisberg.stefan.groupfitness.utils;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.Month;
import org.apache.log4j.Logger;

public class DateManager {
	private int firstDay;
	private int lastDay;
	private int year;
	private Month month;
	private LocalDate date;

	private static Logger log = Logger.getLogger(DateManager.class);
	
	public DateManager(LocalDate date) {
		this.date=date;
		firstDay=1;
		lastDay =date.lengthOfMonth();
		month = date.getMonth();
		year =date.getYear();
		log.info(MessageFormat.format("Original Date : {0}, Year: {1}, LastDay: {2} ", date, year, lastDay ));
	}

	public int getYear() {
		return year;
	}
	
	public Month getMonth() {
		return month;
	}

	public int getLastDayOfMonth() {
		return lastDay;
	}
	public int getFirstDay() {
		return firstDay;
	}
	public void setMonth(int month) {
		this.month=Month.of(month);
		lastDay =this.month.length(false);
	}
}
