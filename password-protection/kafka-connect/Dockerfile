#FROM confluentinc/cp-kafka-connect:5.3.1
FROM confluentinc/cp-server:5.3.1
MAINTAINER pere.urbon@acm.org
ENV container docker

#RUN apt-get update
#RUN apt-get install -y confluent-security

ENV COMPONENT=kafka-connect

RUN echo "===> Installing Schema Registry (for Avro jars) ..." \
    && apt-get -qq update \
    && apt-get install -y \
        confluent-schema-registry=${CONFLUENT_VERSION}${CONFLUENT_PLATFORM_LABEL}-${CONFLUENT_DEB_VERSION} \
    && echo "===> Installing Controlcenter for monitoring interceptors ..."\
    && apt-get install -y confluent-control-center=${CONFLUENT_VERSION}${CONFLUENT_PLATFORM_LABEL}-${CONFLUENT_DEB_VERSION} \
    && echo "===> Installing Confluent Hub client ..."\
    && apt-get install -y confluent-hub-client=${CONFLUENT_VERSION}${CONFLUENT_PLATFORM_LABEL}-${CONFLUENT_DEB_VERSION} \
    && echo "===> Cleaning up ..."  \
    && apt-get clean && rm -rf /tmp/* /var/lib/apt/lists/* \
    echo "===> Setting up ${COMPONENT} dirs ..." \
    && mkdir -p /etc/${COMPONENT} /etc/${COMPONENT}/secrets /etc/${COMPONENT}/jars \
    && chmod -R ag+w /etc/${COMPONENT} /etc/${COMPONENT}/secrets /etc/${COMPONENT}/jars

COPY kafka-connect.properties /tmp/kafka-connect.properties
COPY security.properties /tmp/security.properties
COPY run /etc/my-docker/run

CMD ["/etc/my-docker/run"]
