FROM confluentinc/cp-server:5.3.1
MAINTAINER pere.urbon@acm.org
ENV container docker

COPY zookeeper.properties /tmp/zookeeper.properties
COPY zookeeper.jaas /tmp/zookeeper.jaas
COPY security.properties /tmp/security.properties
COPY log4j.properties /usr/config/log4j.properties
COPY tools-log4j.properties /usr/config/tools-log4j.properties

EXPOSE 2181

ENV KAFKA_OPTS -Djava.security.auth.login.config=/etc/kafka/zookeeper.jaas
COPY run /etc/my-docker/run

CMD ["/etc/my-docker/run"]
