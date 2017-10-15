package org.itstep.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DynamicMessageOfTheDayImpl implements MessageOfTheDayService {
	
	private String[] messages;

	public String getMessage() {

		int day = GregorianCalendar.getInstance().get(Calendar.DAY_OF_WEEK);

		return messages[day - 1];
	}
	
	public DynamicMessageOfTheDayImpl(String[] messages){
		this.messages = messages;
	}
}
