package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/mensagem", new MeuHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Servidor rodando na porta 8080");
    }

    static class MeuHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "Olá do backend sem framework!";

            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            byte[] bytes = response.getBytes("UTF-8");

            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }
}