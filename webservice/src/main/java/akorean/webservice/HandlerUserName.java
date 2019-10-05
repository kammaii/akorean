package akorean.webservice;

import akorean.db.AkoreanDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Set;

public class HandlerUserName implements HttpHandler {

  private AkoreanHttpServer httpServer;
  private AkoreanDAO akoreanDAO;
  HandlerUserName(AkoreanHttpServer akoreanHttpServer,
                  AkoreanDAO akoreanDAO) {
    this.httpServer = akoreanHttpServer;
    this.akoreanDAO = akoreanDAO;
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

        // get individual users
        String urlStr = url.toString();
        int idx = urlStr.lastIndexOf("/");
        String nameStr = urlStr.substring(idx+1);
        System.out.println("USER NAME: " + nameStr);

        Map<String, String> found = akoreanDAO.getByUserName(nameStr);

        if(found != null) {
          String response = gson.toJson(found);
          httpServer.respond200(httpExchange, response);
        } else {
          httpServer.respond404(httpExchange);
        }
    }
  }
}
