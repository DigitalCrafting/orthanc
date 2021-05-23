package org.digitalcrafting.orthanc;

import com.sun.net.httpserver.HttpExchange;
import org.digitalcrafting.orthanc.core.AbstractOrthancHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.digitalcrafting.orthanc.core.ParseUtils.parseRequestParams;

public class HelloHandler extends AbstractOrthancHandler {
    @Override
    public void handleGet(HttpExchange exchange) throws IOException {
        Map<String, List<String>> params = parseRequestParams(exchange.getRequestURI().getRawQuery());
        String noNameText = "Anonymous";
        String name = params.getOrDefault("name", List.of(noNameText)).stream().findFirst().orElse(noNameText);
        String respText = String.format("Hello %s!", name);

        exchange.sendResponseHeaders(200, respText.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = exchange.getResponseBody();
        os.write(respText.getBytes(StandardCharsets.UTF_8));
        os.flush();
        exchange.close();
    }
}
