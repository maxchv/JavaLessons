package org.itstep.service;


public class BasicMessageOfTheDayImpl implements MessageOfTheDayService {
    @Override
    public String getMessage() {
        return "Hello World";
    }
}
