package org.itstep.service;

public class MessagePrinterService {

    private MessageOfTheDayService service;

    public void printMessage() {
        System.out.println(service.getMessage());
    }

    public void setMessageService(MessageOfTheDayService service) {
        this.service = service;
    }

}
