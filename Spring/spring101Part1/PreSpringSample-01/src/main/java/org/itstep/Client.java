package org.itstep;

import org.itstep.service.MessagePrinterService;



public class Client {

	public static void main(final String[] args) {

		MessagePrinterService aMessagePrinter = new MessagePrinterService();
		aMessagePrinter.printMessage();
		

	}
}
