package com.fauxauldrich.kafkaservice.resource;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.fauxauldrich.kafkaservice.model.DynamicProducerModel;
import com.fauxauldrich.kafkaservice.model.DynamicProducerModelWithHeaders;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.header.internals.RecordHeader;
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
    private Handlebars handebarsConfig;

    @PostMapping("/publish")
    public String postSimple(@RequestParam("topic") String TOPIC, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModel payload)
            throws IOException {
        Template template = handebarsConfig.compileInline(payload.getMessage());
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, payload.getBrokers());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> record;

        if (KEY != null) {
            if (PARTITION_ID != null) {
                record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, template.apply(""));
            } else {
                record = new ProducerRecord<>(TOPIC, KEY, template.apply(""));
            }
        } else {
            record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, template.apply(""));
        }
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                producer.flush();
                producer.close();
            }
        });
        return "Published successfully";
    }

    @PostMapping("/publish/with-headers")
    public String postSimpleWithHeaders(@RequestParam("topic") String TOPIC, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModelWithHeaders payload)
            throws IOException {
        Template template = handebarsConfig.compileInline(payload.getMessage());
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, payload.getBrokers());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> record;

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
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                producer.flush();
                producer.close();
            }
        });
        return "Published successfully";
    }

    @PostMapping("/publish/secure")
    public String postSecure(@RequestParam("topic") String TOPIC, @RequestParam() String TRUSTSTORE_LOCATION,
            @RequestParam() String TRUSTSTORE_PASSWORD, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModel payload)
            throws IOException {
        Template template = handebarsConfig.compileInline(payload.getMessage());
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, payload.getBrokers());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, TRUSTSTORE_LOCATION);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, TRUSTSTORE_PASSWORD);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> record;

        if (KEY != null) {
            if (PARTITION_ID != null) {
                record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, template.apply(""));
            } else {
                record = new ProducerRecord<>(TOPIC, KEY, template.apply(""));
            }
        } else {
            record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, template.apply(""));
        }
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                producer.flush();
                producer.close();
            }
        });
        return "Published successfully";
    }

    @PostMapping("/publish/secure/with-headers")
    public String postSecureWithHeaders(@RequestParam("topic") String TOPIC, @RequestParam() String TRUSTSTORE_LOCATION,
            @RequestParam() String TRUSTSTORE_PASSWORD, @RequestParam(required = false) String KEY,
            @RequestParam(required = false) Integer PARTITION_ID, @RequestBody DynamicProducerModelWithHeaders payload)
            throws IOException {
        Template template = handebarsConfig.compileInline(payload.getMessage());
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, payload.getBrokers());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, TRUSTSTORE_LOCATION);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, TRUSTSTORE_PASSWORD);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> record;

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
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                producer.flush();
                producer.close();
            }
        });
        return "Published successfully";
    }
}