package com.bookproject.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertyUtils {

    @Autowired
    private Environment environment;

    public String getApiKey() {
        return environment.getProperty("api_key");
    }
}
