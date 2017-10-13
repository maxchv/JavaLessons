package org.itstep.service;

import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Service;

@Service
public class BasicMessage implements Message {
    public String getMessage() {
        PropertyResolver
        return "Simple message";
    }
}
