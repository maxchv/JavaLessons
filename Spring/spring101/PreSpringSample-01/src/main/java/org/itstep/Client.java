package org.itstep;

import org.itstep.service.MessagePrinterService;

public class Client {
	public static void main(String[] args) {
		MessagePrinterService messagePrinter = new MessagePrinterService();
		messagePrinter.printMessage();
	}
}
