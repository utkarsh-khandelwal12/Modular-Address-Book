package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.net.httpserver.HttpExchange;
import model.Contact;
import service.ContactService;
import service.ContactServiceInterface;
import util.JsonUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class ContactController {
    private static final ContactServiceInterface service = new ContactService();

    public static void create(HttpExchange exchange) {
        try {
            List<Contact> contacts = JsonUtil.fromJson(exchange.getRequestBody(), new TypeReference<>() {
            });
            List<Contact> result = service.create(contacts);
            sendResponse(exchange, result);
        } catch (IOException e) {
            System.out.println("Payload mapping failed. Invalid request " + exchange.getRequestBody().toString());
        } catch (Exception e) {
            System.out.println("Error Occurred while creating contact " + e);
        }
    }

    public static void update(HttpExchange exchange) {
        try {
            List<Contact> updates = JsonUtil.fromJson(exchange.getRequestBody(), new TypeReference<>() {
            });
            List<Contact> result = service.update(updates);
            sendResponse(exchange, result);
        } catch (IOException e) {
            System.out.println("Payload mapping failed. Invalid request " + exchange.getRequestBody().toString());
        } catch (Exception e) {
            System.out.println("Error Occurred while creating contact " + e);
        }
    }

    public static void delete(HttpExchange exchange) {
        try {
            List<String> ids = JsonUtil.fromJson(exchange.getRequestBody(), new TypeReference<>() {});
            int count = service.delete(ids);
            Map<String, Integer> response = Map.of("deleted", count);
            sendResponse(exchange, response);
        } catch (IOException e) {
            System.out.println("Payload mapping failed. Invalid request " + exchange.getRequestBody().toString());
        } catch (Exception e) {
            System.out.println("Error Occurred while creating contact " + e);
        }
    }

    public static void search(HttpExchange exchange) {
        try {
            Map<String, String> query = JsonUtil.fromJson(exchange.getRequestBody(), new TypeReference<>() {
            });
            List<Contact> result = service.search(query.get("query"));
            sendResponse(exchange, result);
        } catch (IOException e) {
            System.out.println("Payload mapping failed. Invalid request " + exchange.getRequestBody().toString());
        } catch (Exception e) {
            System.out.println("Error Occurred while creating contact " + e);
        }
    }

    private static void sendResponse(HttpExchange exchange, Object body) throws IOException {
        String json = JsonUtil.toJson(body);
        byte[] response = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length);
        exchange.getResponseBody().write(response);
        exchange.getResponseBody().close();
    }
}
