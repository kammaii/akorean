package akorean.webservice;

import akorean.db.AkoreanUsersDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.util.*;

public class HandlerUsers implements HttpHandler {

  private AkoreanHttpServer httpServer;
  private AkoreanUsersDAO akoreanUsersDAO;
  HandlerUsers(AkoreanHttpServer akoreanHttpServer,
               AkoreanUsersDAO akoreanUsersDAO) {
    this.httpServer = akoreanHttpServer;
    this.akoreanUsersDAO = akoreanUsersDAO;
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
        Map<String, String> found = new HashMap<>();

        switch (urlKey) {
/*
            case "all" :
                found = akoreanUsersDAO.getUsers();
                break;

            case "id" :
                break;

            case "name" :
                found = akoreanUsersDAO.getUserByName(urlValue);
                break;
*/
            case "email" :
                found = akoreanUsersDAO.getUserByEmail(urlValue);
                break;
        }

        if(found != null) {
            response = gson.toJson(found);
            System.out.println("RESPONSE : " + response);
            httpServer.respond200(httpExchange, response);
        } else {
            httpServer.respond404(httpExchange);
        }


        // 새로운 유저 만들기
    } else if (requestMethod.equalsIgnoreCase("POST")) {

        String requestBody = httpServer.readInputStream(httpExchange.getRequestBody());
        System.out.println("Raw Http Body: " + requestBody);

        switch (url.toString()) {

            case "users" :
                User newUser = gson.fromJson(requestBody, User.class);

                //TODO: !! Definitely need to validate the queryParams!!
                // For example, make sure that username, password, and email are valid and not null.
                akoreanUsersDAO.insertUser(newUser);

                // return the new user as json
                String response = gson.toJson(newUser);
                System.out.println("RESPONSE : " + response);
                httpServer.respond200(httpExchange, response);
                break;

        }

        // 로그인 or 유저 정보 업데이트 하기
    } else if(requestMethod.equalsIgnoreCase("PATCH")) {
        String[] urlSplit = url.toString().split("/");
        String urlKey = urlSplit[2];
        System.out.println("USERS KEY: " + urlKey);
        String response;
        Map<String, String> found = new HashMap<>();

        switch (urlKey) {

            case "login" :
                String userEmail = urlSplit[3];
                String userPass = urlSplit[4];
                found = akoreanUsersDAO.logInCheck(userEmail, userPass);
                break;
        }

        if(found != null) {
            response = gson.toJson(found);
            System.out.println("RESPONSE : " + response);
            httpServer.respond200(httpExchange, response);
        } else {
            httpServer.respond404(httpExchange);
        }
    }
  }
}
