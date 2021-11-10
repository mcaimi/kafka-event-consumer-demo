package com.redhat.kafka;

import com.redhat.kafka.entities.Event;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class EventDeserializer extends ObjectMapperDeserializer<Event> {
    public EventDeserializer() {
        super(Event.class);
    }
}
