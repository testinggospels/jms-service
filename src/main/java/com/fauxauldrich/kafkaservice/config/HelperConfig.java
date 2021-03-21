package com.fauxauldrich.kafkaservice.config;

import com.fauxauldrich.kafkaservice.helpers.KafkaServiceHelper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.helper.StringHelpers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelperConfig {

    @Bean
    public Handlebars handebarsConfig() {
        Handlebars handlebars = new Handlebars();
        handlebars.registerHelpers(StringHelpers.class);
        handlebars.registerHelpers(KafkaServiceHelper.class);
        return handlebars;
    }

}
