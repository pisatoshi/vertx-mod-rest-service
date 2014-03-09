package com.mycompany.myproject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ContainerRequest;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Container;

@Path("test")
public class SampleResource {
    @GET
	@Path("hoge")
	@Produces(MediaType.APPLICATION_JSON)
	public void getQuery(
	        @Suspended final AsyncResponse response,
	        @Context final ContainerRequest jerseyRequest,
	        @Context final HttpServerRequest vertxRequest,
	        @Context final Vertx vertx,
	        @Context final Container container) {

	    vertx.runOnContext(new Handler<Void>() {
	        @Override
	        public void handle(Void aVoid) {
	            response.resume("Hello World!!");
	        }
	    });
	}
}
