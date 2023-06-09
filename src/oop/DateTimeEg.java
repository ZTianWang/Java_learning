package oop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeEg {

	public static void main(String[] args) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-DDD hh:mm:ss a");//D:һ���еĵڼ���
		LocalDateTime dateTime = LocalDateTime.parse("2020-366 11:15:33 ����", formatter);

		System.out.println(dateTime);
		
		formatter = DateTimeFormatter.ofPattern("yyyy��MM��dd�� a hh:mm:ss");
		System.out.println(dateTime.format(formatter));
		
	}
	
	
	
}
