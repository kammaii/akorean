package http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class AkoreanHttpServer {

  // Java Http Servers
  // Tomcat
  // Jetty
  // Instead of using Tomcat or Jetty, this class will provide an embedded Http Server

  public static void main(String[] args) throws IOException {

    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
    HttpContext context = server.createContext("/users");
    context.setHandler(AkoreanHttpServer::handleRequest);
    server.start();
  }

  private static void handleRequest(HttpExchange exchange) throws IOException {

    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();

    //String response = "Hi there!";

    // {"dave" : {"name": "Dave Paroulek", "email": "dparoulek@gmail.com"},
    //  "danny" : {"name": "Danny", "email": "danny@gmail.com" } }

    Map<String, String> username = new HashMap<String, String>();
    username.put("name", "dave");

    String response = gson.toJson(username);

    exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length

    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }


}
