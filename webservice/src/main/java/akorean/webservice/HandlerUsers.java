package akorean.webservice;

import akorean.db.AkoreanDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HandlerUsers implements HttpHandler {

  private AkoreanHttpServer httpServer;
  private AkoreanDAO akoreanDAO;
  HandlerUsers(AkoreanHttpServer akoreanHttpServer,
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

    /*
    Set<String> headers = httpExchange.getRequestHeaders().keySet();
    System.out.println("Request Headers:");
    for(String header : headers) {
      System.out.println("  " + header);
    }
     */

    // Used to serialize java to json
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();

    // 전체 유저를 모두 불러오기
    if(requestMethod.equalsIgnoreCase("GET")) {

      if(url.toString().equals("/users")) {
        String response = gson.toJson(akoreanDAO.getUsers()); // 여기까지가 Response 를 만드는 과정
        System.out.println("Response : " + response);
        httpServer.respond200(httpExchange, response);  // Response 결과를 httpServer 에 보내기

        // 특정 아이디의 유저만 불러오기, "/users/1"
      } else {
        String urlStr = url.toString();
        int idx = urlStr.lastIndexOf("/");
        String idStr = urlStr.substring(idx+1);

        Map<String, String> getUserById = akoreanDAO.getUserById(idStr);

        if(getUserById != null) {
          String response = gson.toJson(getUserById);
          System.out.println("Response : " + response);
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

      //TODO: !! Definitely need to validate the queryParams!!
      // For example, make sure that username, password, and email are valid and not null.
      Map<String, String> newUser = akoreanDAO.insertUser(queryParams);

      // return the new user as json
      String response = gson.toJson(newUser);
      httpServer.respond200(httpExchange, response);
    }
  }
}
