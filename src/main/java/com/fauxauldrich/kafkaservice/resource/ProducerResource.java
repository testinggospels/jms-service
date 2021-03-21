package com.fauxauldrich.kafkaservice.resource;

import java.io.IOException;
import java.util.Map;

import com.fauxauldrich.kafkaservice.model.ProducerModel;
import com.fauxauldrich.kafkaservice.model.ProducerModelWithHeaders;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class ProducerResource {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Object> kafkaSecureTemplate;

    @Autowired
    private Handlebars handebarsConfig;

    @PostMapping("/publish")
    public String postSimple(@RequestParam("topic") String TOPIC, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody ProducerModel payload)
            throws IOException {
        Template template = handebarsConfig.compileInline(payload.getMessage());
        if (KEY != null) {
            if (PARTITION_ID != null) {
                kafkaTemplate.send(TOPIC, PARTITION_ID, KEY, template.apply(""));
            }
            kafkaTemplate.send(TOPIC, KEY, template.apply(""));
        } else {
            kafkaTemplate.send(TOPIC, template.apply(""));
        }
        return "Published successfully";
    }

    @PostMapping("/publish/with-headers")
    public String postSimpleWithHeaders(@RequestParam("topic") String TOPIC, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody ProducerModelWithHeaders payload)
            throws IOException {

        Template template = handebarsConfig.compileInline(payload.getMessage());
        ProducerRecord<String, Object> record;

        if (KEY != null) {
            if (PARTITION_ID != null) {
                record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, template.apply(""));
            } else {
                record = new ProducerRecord<>(TOPIC, KEY, template.apply(""));
            }
        } else {
            record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, template.apply(""));
        }

        for (Map.Entry<String, String> entry : payload.getHeaders().entrySet())
            record.headers().add(new RecordHeader(entry.getKey(), entry.getValue().getBytes()));

        kafkaTemplate.send(record);

        return "Published successfully";
    }

    @PostMapping("/publish/secure")
    public String postSecure(@RequestParam("topic") String TOPIC, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody ProducerModel payload)
            throws IOException {
        Template template = handebarsConfig.compileInline(payload.getMessage());
        if (KEY != null) {
            if (PARTITION_ID != null) {
                kafkaSecureTemplate.send(TOPIC, PARTITION_ID, KEY, template.apply(""));
            }
            kafkaSecureTemplate.send(TOPIC, KEY, template.apply(""));
        } else {
            kafkaSecureTemplate.send(TOPIC, template.apply(""));
        }
        return "Published successfully";
    }

    @PostMapping("/publish/secure/with-headers")
    public String postSecureWithHeaders(@RequestParam("topic") String TOPIC, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody ProducerModelWithHeaders payload)
            throws IOException {

        Template template = handebarsConfig.compileInline(payload.getMessage());
        ProducerRecord<String, Object> record;

        if (KEY != null) {
            if (PARTITION_ID != null) {
                record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, template.apply(""));
            } else {
                record = new ProducerRecord<>(TOPIC, KEY, template.apply(""));
            }
        } else {
            record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, template.apply(""));
        }

        for (Map.Entry<String, String> entry : payload.getHeaders().entrySet())
            record.headers().add(new RecordHeader(entry.getKey(), entry.getValue().getBytes()));

        kafkaSecureTemplate.send(record);

        return "Published successfully";
    }
}
