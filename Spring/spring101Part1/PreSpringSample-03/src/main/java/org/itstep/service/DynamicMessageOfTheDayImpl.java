package org.itstep.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DynamicMessageOfTheDayImpl implements MessageOfTheDayService {


    private final String[] messages = new String[]{
            "Sunday morning message!",
            "Monday morning message!",
            "Tuesday morning message!",
            "Wednesday morning message!",
            "Thursday morning message!",
            "Friday morning message!",
            "Saturday morning message!"
    };


    public String getMessage() {
        int day = GregorianCalendar.getInstance().get(Calendar.DAY_OF_WEEK);

        String message = messages[day - 1];
        return message;
    }

}
