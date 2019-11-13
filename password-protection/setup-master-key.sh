#!/usr/bin/env bash
set -e

echo "-> PASSPHRASE generation <-"
rm -f security.properties master.key
confluent secret master-key generate \
  --local-secrets-file ./security.properties \
  --passphrase  @secret.properties > master.key

export CONFLUENT_SECURITY_MASTER_KEY=`cat master.key | tail -n 2 | head -n 1 | cut  -d "|" -f 3 |  sed -e 's/^[[:space:]]*//'`

echo "-> security file distribution <-"
cp security.properties ./zookeeper/security.properties
cp security.properties ./kafka/security.properties

echo "-> copy original files to final destination in each component  <- "

cp ./kafka/server-cleartext.properties ./kafka/server.properties
cp ./zookeeper/zookeeper-cleartext.properties ./zookeeper/zookeeper.properties

echo "... DONE"
