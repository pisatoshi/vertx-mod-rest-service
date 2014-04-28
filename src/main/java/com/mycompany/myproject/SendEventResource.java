package com.mycompany.myproject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.HttpServerResponse;
import org.vertx.java.core.streams.ReadStream;
import org.vertx.java.platform.Container;

@Path("event")
public class SendEventResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void sendEventbus(
            @Suspended final AsyncResponse response,
            @Context final Vertx vertx,
            @Context final Container container,
            @Context final HttpServerRequest vertxRequest,
            @Context final HttpServerResponse vertxResponse,
            @Context final ReadStream<HttpServerRequest> vertxStream) {

        vertx.runOnContext(new Handler<Void>() {
            @Override
            public void handle(Void aVoid) {
                container.logger().info("request message: ");
                vertx.eventBus().send("vertx.mod.testexample", "TEST",
                        new Handler<Message<String>>() {
                    @Override
                    public void handle(Message<String> reply) {
                        container.logger().info("message responded.");
                        response.resume(reply.body());
                    }
                });
                container.logger().info("message sent.");
            }
        });
    }
}
