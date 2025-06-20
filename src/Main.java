import com.sun.net.httpserver.HttpServer;
import controller.ContactController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);

        server.createContext("/create", ContactController::create);
        server.createContext("/update", ContactController::update);
        server.createContext("/delete", ContactController::delete);
        server.createContext("/search", ContactController::search);

        server.setExecutor(null);
        server.start();
        System.out.println("Server running on port 5000...");
    }
}