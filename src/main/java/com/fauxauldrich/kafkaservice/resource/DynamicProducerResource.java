package com.fauxauldrich.kafkaservice.resource;

import java.io.IOException;

import com.fauxauldrich.kafkaservice.model.DynamicProducerModel;
import com.fauxauldrich.kafkaservice.model.DynamicProducerModelWithHeaders;
import com.fauxauldrich.kafkaservice.service.DynamicProducerService;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/dynamic")
public class DynamicProducerResource {

    @Autowired
    private DynamicProducerService producerService;

    @Autowired
    private Handlebars handebarsConfig;

    @PostMapping("/publish")
    public String postSimple(@RequestParam("topic") String TOPIC, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModel payload)
            throws IOException {

        Template template = handebarsConfig.compileInline(payload.getMessage());

        producerService.publish(payload.getBrokers(), TOPIC, KEY, template.apply(""), PARTITION_ID);

        return "Published successfully";
    }

    @PostMapping("/publish/with-headers")
    public String postSimpleWithHeaders(@RequestParam("topic") String TOPIC, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModelWithHeaders payload)
            throws IOException {

        Template template = handebarsConfig.compileInline(payload.getMessage());

        producerService.publishWithHeaders(payload.getBrokers(), TOPIC, KEY, template.apply(""), PARTITION_ID,
                payload.getHeaders());

        return "Published successfully";
    }

    @PostMapping("/publish/secure")
    public String postSecure(@RequestParam("topic") String TOPIC, @RequestParam() String TRUSTSTORE_LOCATION,
            @RequestParam() String TRUSTSTORE_PASSWORD, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModel payload)
            throws IOException {

        Template template = handebarsConfig.compileInline(payload.getMessage());

        producerService.publishSecure(payload.getBrokers(), TOPIC, KEY, template.apply(""), PARTITION_ID,
                TRUSTSTORE_LOCATION, TRUSTSTORE_PASSWORD);

        return "Published successfully";
    }

    @PostMapping("/publish/secure/with-headers")
    public String postSecureWithHeaders(@RequestParam("topic") String TOPIC, @RequestParam() String TRUSTSTORE_LOCATION,
            @RequestParam() String TRUSTSTORE_PASSWORD, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModelWithHeaders payload)
            throws IOException {

        Template template = handebarsConfig.compileInline(payload.getMessage());

        producerService.publishSecureWithHeaders(payload.getBrokers(), TOPIC, KEY, template.apply(""), PARTITION_ID,
                TRUSTSTORE_LOCATION, TRUSTSTORE_PASSWORD, payload.getHeaders());

        return "Published successfully";
    }
}
