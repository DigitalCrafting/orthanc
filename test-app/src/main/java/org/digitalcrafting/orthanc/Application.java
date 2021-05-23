package org.digitalcrafting.orthanc;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application {
    public static void main(String[] args) throws IOException {
        final TestHandler testHandler = new TestHandler();
        final HelloHandler helloHandler = new HelloHandler();

        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpContext context = server.createContext("/api/hello", helloHandler);
        context.setAuthenticator(new BasicAuthenticator("myrealm") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals("admin") && pwd.equals("admin");
            }
        });
        server.createContext("/api/test", testHandler);

        server.setExecutor(null);
        server.start();
    }
}
