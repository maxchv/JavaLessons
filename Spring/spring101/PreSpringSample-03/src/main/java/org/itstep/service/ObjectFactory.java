package org.itstep.service;

public class ObjectFactory {
    public static MessagePrinterService getInstance(Class<? extends MessagePrinterService> messagePrinterClazz,
                                                    Class<? extends MessageOfTheDayService> messageOfTheDayClazz) {

        MessagePrinterService aMessagePrinterService = getInstance(messagePrinterClazz);
        MessageOfTheDayService aMessageOfTheDayService = getInstance(messageOfTheDayClazz);
        aMessagePrinterService.setMessageService(aMessageOfTheDayService);
        return aMessagePrinterService;
    }

    private static <T> T getInstance(Class<T> type) {
        T newInstance = null;
        if (type != null) {
            try {
                newInstance = type.newInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return newInstance;
    }
}
