package com.redhat.kafka;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.kafka.entities.Event;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;

import io.smallrye.mutiny.Multi;

@Path("/consumer")
public class EventConsumerResource {

    @Channel("event-input-topic")
    Multi<Event> events;

    @GET
    @Path("/get")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("application/json")
    public Multi<Event> eventStream() {
      return events;
    }
}
