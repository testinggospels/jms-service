package com.testinggospels.jmsservice.config;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.testinggospels.jmsservice.helpers.JMSServiceHelper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelperConfig {

    @Bean
    public Handlebars handebarsConfig() {
        Handlebars handlebars = new Handlebars();
        handlebars.registerHelpers(StringHelpers.class);
        handlebars.registerHelpers(JMSServiceHelper.class);
        return handlebars;
    }

}
