package org.itstep;

import org.itstep.service.DynamicMessageOfTheDayImpl;
import org.itstep.service.MessagePrinterService;
import org.itstep.service.ObjectFactory;


public class Client {

    public static void main(final String[] args) {

        //configuration part we can then swap impl with DynamicMessageOfTheDayImpl.class
        final MessagePrinterService aMessagePrinter = ObjectFactory
                .getInstance(MessagePrinterService.class, DynamicMessageOfTheDayImpl.class);

        //doing something part...
        aMessagePrinter.printMessage();
    }
}
