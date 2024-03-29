# Quarkus Kafka Consumer Demo

This is a simple example of how to build a Kafka consumer with Quarkus and Tekton.

This demo implements a small Quarkus App that connects to a Kafka broker and receives a stream of events from a specific topic. Events are streamed as Server Sent Events and displayed on a web page via Angular asynchronous calls.

The "event" message is a JSon-formatted payload that contains

* an unique UUID that identifies an event stream
* a message string
* an event severity index (integer)
* the event timestamp

The kafka message key is set to be equal to the UUID contained in the payload.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

Running the application in dev mode requires an already running Kafka Broker. A simple one-node broker running on localhost is enough.
To install AMQ Streams on a RedHat Enterprise Linux 8 server, refer to [this](https://github.com/mcaimi/amq-streams-aio-ansible) Ansible Playbook.

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Testing the application

Some simple automated tests are shipped as JUnit test cases. To run tests simply use maven:
```shell script
./mvnw clean test
```
An embedded Kafka Cluster is automatically started as a Quarkus Resource for the Test Lifecycle Manager.

## Creating a native executable

You can create a native executable using:
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/consumer-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Building the artifact using Tekton on Openshift Container Platform

Please follow the steps described [here](https://github.com/mcaimi/k8s-demo-argocd) to deploy the CI environment.
The repo contains automation that deploys the CI env, the application manifests and the tektok pipeline used to build the app itself.

## Related Guides

- RESTEasy JAX-RS ([guide](https://quarkus.io/guides/rest-json)): REST endpoint framework implementing JAX-RS and more
- Kafka for JUnit ([docs](https://mguenther.github.io/kafka-junit/#section:introduction)): Embedded Kafka for JUnit
- Vert.x under Quarkus ([docs](https://quarkus.io/guides/vertx-reference)): Use Vert.x under Quarkus reference docs
- Quarkus Kafka Guide ([docs](https://quarkus.io/guides/kafka)): Guide for using Kafka under Quarkus

## Provided Code

The application comes with a simple Angular frontend listening on the default quarkus HTTP server port:

![Web Frontend](/assets/consumer-frontend.png)

