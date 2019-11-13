# Password protection flow


1.- Run the script `./setup-master.key.sh` to generate the master key.

This script will generate two files:
* security.properties (to be distributed to each of the components)
* master.key (holding the master key, this file is only for demo purposes. Master key should not be stored in disk for production )

As well this script will distribute the _security.properties_ file to each of the components.

2.- Export the Master Key as environment variables.

* The master key has been saved in the _master.key_ file.
* Execute a command like `export CONFLUENT_SECURITY_MASTER_KEY=[generated_master_key]`

3.- Run the _./up_ script to start up all the components

NOTE: The _./up_ script will encrypt a soma parameters within the component properties files and setup all the necessary configuration setup.

To check everything is up and running properly a command like:

```bash
➜  password-protection git:(password-protection) ✗ docker ps -a
CONTAINER ID        IMAGE                           COMMAND                CREATED             STATUS              PORTS                    NAMES
8ccc738e4be0        password-protection_kafka       "/etc/my-docker/run"   3 minutes ago       Up 3 minutes        0.0.0.0:9092->9092/tcp   kafka
8833f8253d23        password-protection_zookeeper   "/etc/my-docker/run"   5 minutes ago       Up 5 minutes        2181/tcp, 9092/tcp      zookeeper
```
 will show all components up and running.

A command like:

```bash
docker-compose exec kafka kafka-console-producer --broker-list kafka:9092 --topic test-topic --producer.config=/etc/kafka/kafka.properties
```

can be used to produce message to the newly created cluster.
