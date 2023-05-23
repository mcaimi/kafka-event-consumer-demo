package com.redhat.kafka;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.redhat.kafka.entities.Event;
import org.eclipse.microprofile.reactive.messaging.Channel;
//import org.jboss.resteasy.reactive.annotations.SseElementType;

import io.smallrye.mutiny.Multi;

@Path("/consumer")
public class EventConsumerResource {

    @Channel("event-input-topic")
    Multi<Event> events;

    @GET
    @Path("/get")
    @Produces(MediaType.SERVER_SENT_EVENTS)
   // @SseElementType("application/json")
    public Multi<Event> eventStream() {
      return events;
    }
}
