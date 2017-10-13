package org.itstep;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.itstep.service.BasicMessageOfTheDayImpl;

public class Client {

	public static void main(final String[] args) {

		
		//configuration part
		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring/application.xml")){
			context.registerShutdownHook();						
			/*MessagePrinter printer = context.getBean(MessagePrinter.class);*/
			
			BasicMessageOfTheDayImpl concreteService = context.getBean("basicMessageOfDay", BasicMessageOfTheDayImpl.class);
			concreteService.setMessage("Message from the Den!!");
			System.out.println(concreteService.getMessage());
			
			BasicMessageOfTheDayImpl concreteService2 = context.getBean("basicMessageOfDay", BasicMessageOfTheDayImpl.class);
			System.out.println(concreteService2.getMessage());
			
			
			//doing something part...
			/*printer.printMessage();*/
			
		}catch(final Exception ex){
			ex.printStackTrace();
		}
		

	}
}
