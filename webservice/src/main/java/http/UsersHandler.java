package http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UsersHandler implements HttpHandler {

  private AkoreanHttpServer httpServer;

  UsersHandler(AkoreanHttpServer httpServer) {
    this.httpServer = httpServer;
  }

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {

    System.out.println();
    System.out.println("------------------------");

    URI url = httpExchange.getRequestURI();
    System.out.println("Handling Request for: '" + url + "'");

    String requestMethod = httpExchange.getRequestMethod();
    System.out.println("Request Type: '" + requestMethod + "'");

    Set<String> headers = httpExchange.getRequestHeaders().keySet();
    System.out.println("Request Headers:");
    for(String header : headers) {
      System.out.println("  " + header);
    }

    // Used to serialize java to json
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();

    // return list of all users
    if(requestMethod.equalsIgnoreCase("GET")) {


      if(url.equals("/users")) {
        String response = gson.toJson(httpServer.getUsers());
        httpServer.respond200(httpExchange, response);
      } else {
        // get individual users
        String urlStr = url.toString();
        int idx = urlStr.lastIndexOf("/");
        String idStr = urlStr.substring(idx+1);
        System.out.println("USER ID: " + idStr);

        List<Map<String, String>> users = httpServer.getUsers();
        Map<String, String> found = null;
        for(Map<String, String> user : users) {
          if(user.get("id").equals(idStr)) {
            found = user;
          }
        }

        if(found != null) {
          String response = gson.toJson(found);
          httpServer.respond200(httpExchange, response);
        } else {
          httpServer.respond404(httpExchange);
        }
      }

    }

    // create new user
    if(requestMethod.equalsIgnoreCase("POST")) {

      String results = httpServer.readInputStream(httpExchange.getRequestBody());
      System.out.println("Raw Http Body: " + results);

      Map<String, String> queryParams = httpServer.splitQueryStr(results);
      System.out.println("Parsed Http Body:");
      for(String key : queryParams.keySet()) {
        System.out.println("  " + key + ": '" + queryParams.get(key) + "'");
      }

      List<Map<String, String>> users = httpServer.getUsers();
      queryParams.put("id", httpServer.getUniqueId().toString());
      users.add(queryParams);

      // return the new user as json
      String response = gson.toJson(queryParams);
      System.out.println("Attempting to send response: " + response);
      httpServer.respond200(httpExchange, response);

    }

  }
}
