package org.itstep.service;

import java.util.Calendar;

public class DynamicMessageOfTheDayImpl {

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
        //Какой сегодня день??
        final int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        final String message = messages[day - 1];
        return message;
    }

}
