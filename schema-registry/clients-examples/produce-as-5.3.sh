#!/usr/bin/env bash

mvn clean assembly:assembly

JAVA_OPTS="-Djavax.net.ssl.trustStore=secrets/client.truststore
            -Djavax.net.ssl.trustStorePassword=confluent
            -Djavax.net.ssl.keyStore=secrets/client.keystore
            -Djavax.net.ssl.keyStorePassword=confluent"

java $JAVA_OPTS -cp target/ClientExamples-jar-with-dependencies.jar com.purbon.kafka.tls.KafkaProducerExample
