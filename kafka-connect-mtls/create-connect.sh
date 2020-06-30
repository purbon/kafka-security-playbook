#!/usr/bin/env bash

OPTS="--key connect/secrets/connect.key.pem --cacert connect/secrets/ca-chain.cert.pem --cert connect/secrets/connect.cert.pem:confluent"
#OPTS="--cert ./cert.pem --key key.pem --cacert connect/secrets/ca-chain.cert.pem"
PORT=18083

echo "read connector"

curl $OPTS https://localhost:$PORT/connectors

echo "configure connector"

curl -XPOST $OPTS -H 'Content-Type:application/json' -d @connector.json https://localhost:$PORT/connectors
