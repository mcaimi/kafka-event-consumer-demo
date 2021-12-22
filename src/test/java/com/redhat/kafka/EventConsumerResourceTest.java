package com.redhat.kafka;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(EmbeddedKafkaResource.class)
public class EventConsumerResourceTest {

    @Test
    public void testWrongConsumerEndpoint() {
        given()
          .when().get("/get")
          .then()
             .statusCode(404);
    }

    @Test
    public void testConsumerEndpoint() {
        given()
          .when().get("/consumer/get")
          .then()
             .statusCode(200);
    }
}