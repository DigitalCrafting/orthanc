package org.digitalcrafting.orthanc.core;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public abstract class AbstractOrthancHandler implements OrthancHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (HttpMethod.GET.equals(exchange.getRequestMethod())) {
            this.handleGet(exchange);
        } else if (HttpMethod.POST.equals(exchange.getRequestMethod())) {
            this.handlePost(exchange);
        } else if (HttpMethod.PUT.equals(exchange.getRequestMethod())) {
            this.handlePut(exchange);
        } else if (HttpMethod.PATCH.equals(exchange.getRequestMethod())) {
            this.handlePatch(exchange);
        } else if (HttpMethod.DELETE.equals(exchange.getRequestMethod())) {
            this.handleDelete(exchange);
        } else if (HttpMethod.OPTIONS.equals(exchange.getRequestMethod())) {
            this.handleOptions(exchange);
        } else {
            exchange.sendResponseHeaders(405, -1);
        }
    }
}
