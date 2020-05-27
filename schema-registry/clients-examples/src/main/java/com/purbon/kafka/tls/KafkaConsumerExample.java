package com.purbon.kafka.tls;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import java.util.Arrays;
import java.util.Properties;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;

public class KafkaConsumerExample {

  public static Properties config() {
    Properties props = new Properties();

    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group" + System.currentTimeMillis());
    props.put(
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Serdes.String().deserializer().getClass());
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
    props.put("schema.registry.url", "https://localhost:8099");

    props.put("schema.registry.ssl.truststore.location", "secrets/client.truststore");
    props.put("schema.registry.ssl.truststore.password", "confluent");
    props.put("schema.registry.ssl.keystore.location", "secrets/client.keystore");
    props.put("schema.registry.ssl.keystore.password", "confluent");
    props.put("schema.registry.ssl.key.password", "confluent");

    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

    return props;
  }

  private static String TOPIC = "topic.avro";

  public static void main(String[] args) {

    final Consumer<String, GenericRecord> consumer =
        new KafkaConsumer<String, GenericRecord>(config());
    consumer.subscribe(Arrays.asList(TOPIC));

    try {
      while (true) {
        ConsumerRecords<String, GenericRecord> records = consumer.poll(100);
        for (ConsumerRecord<String, GenericRecord> record : records) {
          System.out.printf(
              "offset = %d, key = %s, value = %s \n",
              record.offset(), record.key(), record.value());
        }
      }
    } finally {
      consumer.close();
    }
  }
}
