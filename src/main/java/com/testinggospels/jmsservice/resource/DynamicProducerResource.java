package com.testinggospels.jmsservice.resource;

import java.io.IOException;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.testinggospels.jmsservice.model.DynamicProducerModel;
import com.testinggospels.jmsservice.model.DynamicProducerModelWithHeaders;
import com.testinggospels.jmsservice.service.DynamicProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/kafka/dynamic")
public class DynamicProducerResource {

    @Autowired
    private DynamicProducerService producerService;

    @Autowired
    private Handlebars handebarsConfig;

    @PostMapping("/publish")
    @Timed(description = "publish_dynamic")
    public String postSimple(@RequestParam("topic") String TOPIC, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModel payload)
            throws IOException {

        Template template = handebarsConfig.compileInline(payload.getMessage());
        Template keyTemplate = handebarsConfig.compileInline(KEY);

        producerService.publish(payload.getBrokers(), TOPIC, keyTemplate.apply(""), template.apply(""), PARTITION_ID);

        return "Published successfully";
    }

    @PostMapping("/publish/with-headers")
    @Timed(description = "publish_dynamic_with_headers")
    public String postSimpleWithHeaders(@RequestParam("topic") String TOPIC, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModelWithHeaders payload)
            throws IOException {

        Template template = handebarsConfig.compileInline(payload.getMessage());
        Template keyTemplate = handebarsConfig.compileInline(KEY);

        producerService.publishWithHeaders(payload.getBrokers(), TOPIC, keyTemplate.apply(""), template.apply(""),
                PARTITION_ID, payload.getHeaders());

        return "Published successfully";
    }

    @PostMapping("/publish/secure")
    @Timed(description = "publish_dynamic_secure")
    public String postSecure(@RequestParam("topic") String TOPIC, @RequestParam() String TRUSTSTORE_LOCATION,
            @RequestParam() String TRUSTSTORE_PASSWORD, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModel payload)
            throws IOException {

        Template template = handebarsConfig.compileInline(payload.getMessage());
        Template keyTemplate = handebarsConfig.compileInline(KEY);

        producerService.publishSecure(payload.getBrokers(), TOPIC, keyTemplate.apply(""), template.apply(""),
                PARTITION_ID, TRUSTSTORE_LOCATION, TRUSTSTORE_PASSWORD);

        return "Published successfully";
    }

    @PostMapping("/publish/secure/with-headers")
    @Timed(description = "publish_dynamic_secure_with_headers")
    public String postSecureWithHeaders(@RequestParam("topic") String TOPIC, @RequestParam() String TRUSTSTORE_LOCATION,
            @RequestParam() String TRUSTSTORE_PASSWORD, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModelWithHeaders payload)
            throws IOException {

        Template template = handebarsConfig.compileInline(payload.getMessage());
        Template keyTemplate = handebarsConfig.compileInline(KEY);

        producerService.publishSecureWithHeaders(payload.getBrokers(), TOPIC, keyTemplate.apply(""), template.apply(""),
                PARTITION_ID, TRUSTSTORE_LOCATION, TRUSTSTORE_PASSWORD, payload.getHeaders());

        return "Published successfully";
    }
}
