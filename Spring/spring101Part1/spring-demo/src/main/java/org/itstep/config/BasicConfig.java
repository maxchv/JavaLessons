package org.itstep.config;

import org.itstep.service.BasicMessage;
import org.itstep.service.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertyResolver;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.ResourceBundle;

@Configuration
public class BasicConfig {

    @Autowired
    PropertyResolver propertyResolver;

    @Bean
    Message getMessage() {
        return new BasicMessage();
    }

}
