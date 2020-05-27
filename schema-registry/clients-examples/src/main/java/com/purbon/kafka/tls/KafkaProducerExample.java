package com.purbon.kafka.tls;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.Properties;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;

public class KafkaProducerExample {

  public static Properties config() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Serdes.String().serializer().getClass());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
    props.put("schema.registry.url", "https://localhost:8099");

    props.put("schema.registry.ssl.truststore.location", "secrets/client.truststore");
    props.put("schema.registry.ssl.truststore.password", "confluent");
    props.put("schema.registry.ssl.keystore.location", "secrets/client.keystore");
    props.put("schema.registry.ssl.keystore.password", "confluent");
    props.put("schema.registry.ssl.key.password", "confluent");
    return props;
  }

  private static String OUTPUT_TOPIC = "topic.avro";

  public static void main(String[] args) {

    KafkaProducer producer = new KafkaProducer(config());

    String key = "key1";
    String userSchema =
        "{\"type\":\"record\","
            + "\"name\":\"myrecord\","
            + "\"fields\":[{\"name\":\"f1\",\"type\":\"string\"}]}";

    Schema.Parser parser = new Schema.Parser();
    Schema schema = parser.parse(userSchema);

    String[] values = new String[] {"value1", "value2", "value3"};

    for (String value : values) {
      GenericRecord avroRecord = new GenericData.Record(schema);
      avroRecord.put("f1", value);
      ProducerRecord<Object, Object> record =
          new ProducerRecord<Object, Object>(OUTPUT_TOPIC, key, avroRecord);
      producer.send(record);
    }
    producer.flush();
    producer.close();
  }
}
