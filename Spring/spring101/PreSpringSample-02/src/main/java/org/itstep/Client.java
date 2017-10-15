package org.itstep;

import org.itstep.service.BasicMessageOfTheDayImpl;
import org.itstep.service.DynamicMessageOfTheDayImpl;
import org.itstep.service.MessageOfTheDayService;
import org.itstep.service.MessagePrinterService;


public class Client {

	public static void main(String[] args) {
		MessageOfTheDayService messageService = new BasicMessageOfTheDayImpl();
		//MessageOfTheDayService messageService = new DynamicMessageOfTheDayImpl();

		MessagePrinterService aMessagePrinter = new MessagePrinterService();
		aMessagePrinter.setMessageService(messageService);
		aMessagePrinter.printMessage();
	}
}
