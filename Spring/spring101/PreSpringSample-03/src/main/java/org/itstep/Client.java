package org.itstep;

import org.itstep.service.DynamicMessageOfTheDayImpl;
import org.itstep.service.MessagePrinterService;
import org.itstep.service.ObjectFactory;


public class Client {

    public static void main(String[] args) {

        // В последствии можно заменить DynamicMessageOfTheDayImpl.class на другой класс реализующий
        // интерфейс MessageOfTheDayService
        MessagePrinterService messagePrinter = ObjectFactory
                .getInstance(MessagePrinterService.class, DynamicMessageOfTheDayImpl.class);

        messagePrinter.printMessage();
    }
}
