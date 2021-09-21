package com.testinggospels.jmsservice.service;

import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.stereotype.Service;

@Service
public class DynamicProducerService {

    public void publish(String BROKERS, String TOPIC, String KEY, String MESSAGE, Integer PARTITION_ID) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKERS);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> record;

        if (KEY != null) {
            if (PARTITION_ID != null) {
                record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, MESSAGE);
            } else {
                record = new ProducerRecord<>(TOPIC, KEY, MESSAGE);
            }
        } else {
            record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, MESSAGE);
        }
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                producer.flush();
                producer.close();
            }
        });
    }

    public void publishWithHeaders(String BROKERS, String TOPIC, String KEY, String MESSAGE, Integer PARTITION_ID,
            Map<String, String> HEADERS) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKERS);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> record;

        if (KEY != null) {
            if (PARTITION_ID != null) {
                record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, MESSAGE);
            } else {
                record = new ProducerRecord<>(TOPIC, KEY, MESSAGE);
            }
        } else {
            record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, MESSAGE);
        }
        for (Map.Entry<String, String> entry : HEADERS.entrySet())
            record.headers().add(new RecordHeader(entry.getKey(), entry.getValue().getBytes()));
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                producer.flush();
                producer.close();
            }
        });
    }

    public void publishSecure(String BROKERS, String TOPIC, String KEY, String MESSAGE, Integer PARTITION_ID,
            String TRUSTSTORE_LOCATION, String TRUSTSTORE_PASSWORD) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKERS);
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
                record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, MESSAGE);
            } else {
                record = new ProducerRecord<>(TOPIC, KEY, MESSAGE);
            }
        } else {
            record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, MESSAGE);
        }
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                producer.flush();
                producer.close();
            }
        });
    }

    public void publishSecureWithHeaders(String BROKERS, String TOPIC, String KEY, String MESSAGE, Integer PARTITION_ID,
            String TRUSTSTORE_LOCATION, String TRUSTSTORE_PASSWORD, Map<String, String> HEADERS) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKERS);
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
                record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, MESSAGE);
            } else {
                record = new ProducerRecord<>(TOPIC, KEY, MESSAGE);
            }
        } else {
            record = new ProducerRecord<>(TOPIC, PARTITION_ID, KEY, MESSAGE);
        }
        for (Map.Entry<String, String> entry : HEADERS.entrySet())
            record.headers().add(new RecordHeader(entry.getKey(), entry.getValue().getBytes()));
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                producer.flush();
                producer.close();
            }
        });
    }
}
