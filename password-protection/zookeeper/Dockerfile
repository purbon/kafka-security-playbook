FROM confluentinc/cp-server:5.3.1
MAINTAINER pere.urbon@acm.org
ENV container docker

COPY zookeeper.properties /tmp/zookeeper.properties
COPY zookeeper.jaas /tmp/zookeeper.jaas

EXPOSE 2181

ENV KAFKA_OPTS -Djava.security.auth.login.config=/etc/kafka/zookeeper.jaas
COPY run /etc/my-docker/run

CMD ["/etc/my-docker/run"]
