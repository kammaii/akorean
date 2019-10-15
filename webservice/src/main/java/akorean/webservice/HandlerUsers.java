package akorean.webservice;

import akorean.db.AkoreanDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
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

    // GET 으로 요청이 들어왔을 때, 유저 정보 가져오기
    if(requestMethod.equalsIgnoreCase("GET")) {

        String[] urlSplit = url.toString().split("/");
        String urlKey = urlSplit[2];
        String urlValue = urlSplit[3];
        System.out.println("USERS KEY: " + urlKey);
        System.out.println("USERS VALUE: " + urlValue);
        String response;
        List<Map<String, String>> found = new ArrayList<>();

        switch (urlKey) {

            case "all" :
                found = akoreanDAO.getUsers();
                break;

            case "id" :
                found = akoreanDAO.getUserById(urlValue);
                break;

            case "name" :
                found = akoreanDAO.getUserByName(urlValue);
                break;

            case "email" :
                found = akoreanDAO.getUserByEmail(urlValue);
                break;
        }

        // Response 결과를 httpServer 에 보내기
        if(found != null) {
            response = gson.toJson(found);
            System.out.println("RESPONSE : " + response);
            httpServer.respond200(httpExchange, response);
        } else {
            httpServer.respond404(httpExchange);
        }

        // create new user
    } else if (requestMethod.equalsIgnoreCase("POST")) {
        String requestBody = httpServer.readInputStream(httpExchange.getRequestBody());
        System.out.println("Raw Http Body: " + requestBody);
        User newUser = gson.fromJson(requestBody, User.class);

        //TODO: !! Definitely need to validate the queryParams!!
        // For example, make sure that username, password, and email are valid and not null.
        akoreanDAO.insertUser(newUser);

        // return the new user as json
        String response = gson.toJson(newUser);
        System.out.println("RESPONSE : " + response);
        httpServer.respond200(httpExchange, response);
    }
  }
}
