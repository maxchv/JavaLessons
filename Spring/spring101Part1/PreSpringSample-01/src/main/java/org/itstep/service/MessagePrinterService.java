package org.itstep.service;


public class MessagePrinterService {
    private final BasicMessageOfTheDayImpl service = new BasicMessageOfTheDayImpl();

    public MessagePrinterService() {
    }

    public void printMessage() {
        System.out.println(service.getTextMessage());
    }
}
