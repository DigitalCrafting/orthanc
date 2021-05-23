package org.digitalcrafting.orthanc;

import com.sun.net.httpserver.HttpExchange;
import org.digitalcrafting.orthanc.core.AbstractOrthancHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TestHandler extends AbstractOrthancHandler {
    @Override
    public void handleGet(HttpExchange exchange) throws IOException {
        String resp = "Test!\n";
        exchange.sendResponseHeaders(200, resp.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = exchange.getResponseBody();
        os.write(resp.getBytes(StandardCharsets.UTF_8));
        os.flush();
        exchange.close();
    }
}
