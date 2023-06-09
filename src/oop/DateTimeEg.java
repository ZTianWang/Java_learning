package oop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeEg {

	public static void main(String[] args) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-DDD hh:mm:ss a");//D:一年中的第几天
		LocalDateTime dateTime = LocalDateTime.parse("2020-366 11:15:33 下午", formatter);

		System.out.println(dateTime);
		
		formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 a hh:mm:ss");
		System.out.println(dateTime.format(formatter));
		
	}
	
	
	
}
