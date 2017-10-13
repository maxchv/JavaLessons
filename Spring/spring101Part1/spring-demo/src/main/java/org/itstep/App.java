package org.itstep;

import org.itstep.config.BasicConfig;
import org.itstep.service.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(BasicConfig.class);
        Message msg = ctx.getBean(Message.class);
        System.out.println(msg.getMessage());


    }
}
