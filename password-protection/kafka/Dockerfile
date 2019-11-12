FROM confluentinc/cp-server:5.3.1
MAINTAINER pere.urbon@acm.org
ENV container docker

EXPOSE 9092

COPY server.properties /tmp/server.properties
COPY kafka.jaas.config /tmp/kafka_server_jaas.conf
COPY log4j.properties /tmp/log4j.properties
COPY kafka.properties /tmp/kafka.properties
COPY security.properties /tmp/security.properties

ENV KAFKA_OPTS -Djava.security.auth.login.config=/etc/kafka/kafka_server_jaas.conf

COPY run /etc/my-docker/run

CMD ["/etc/my-docker/run"]
