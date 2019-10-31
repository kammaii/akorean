package akorean.webservice;

import akorean.db.AkoreanCollectionsDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

public class HandlerCollections implements HttpHandler {

  private AkoreanHttpServer httpServer;
  private AkoreanCollectionsDAO akoreanCollectionsDAO;
  HandlerCollections(AkoreanHttpServer akoreanHttpServer,
                     AkoreanCollectionsDAO akoreanDAO) {
    this.httpServer = akoreanHttpServer;
    this.akoreanCollectionsDAO = akoreanDAO;
  }


  @Override
  public void handle(HttpExchange httpExchange) throws IOException {

      System.out.println();
      System.out.println("------------------------");

      URI url = httpExchange.getRequestURI();
      System.out.println("Handling Request for: '" + url + "'");

      String requestMethod = httpExchange.getRequestMethod();
      System.out.println("Request Type: '" + requestMethod + "'");

      // Used to serialize java to json
      GsonBuilder gsonBuilder = new GsonBuilder();
      Gson gson = gsonBuilder.create();

      // 현재 날짜 구하기
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      Date time = new Date();
      String dateNow = format.format(time);

      if(requestMethod.equalsIgnoreCase("GET")) {

          // 다운로드 할 아이템 찾기
          String[] urlSplit = url.toString().split("/");
          String dateLastSync = urlSplit[2];
          System.out.println("dateLastSync: " + dateLastSync);
          System.out.println("dateNow: " + dateNow);
          String response;
          List<Map<String, String>> found = akoreanCollectionsDAO.getDownloadItems(dateLastSync, dateNow);

          if(found != null) {
              response = gson.toJson(found);
              System.out.println("RESPONSE : " + response);
              httpServer.respond200(httpExchange, response);
          } else {
              httpServer.respond404(httpExchange);
          }

      }else if (requestMethod.equalsIgnoreCase("POST")) {

          String requestBody = httpServer.readInputStream(httpExchange.getRequestBody());
          System.out.println("Raw Http Body: " + requestBody);

          List<Collection> collections = gson.fromJson(requestBody, new TypeToken<List<Collection>>(){}.getType());
          List<Collection> collectionsToRespond = new ArrayList<>();

          // 업로드 하기
          System.out.println("Attempting to insert new collections");
          for(Collection collection : collections) {
              collection.setDateSync(dateNow);  // 받은 컬렉션에 현재 시간 도장 찍기
              akoreanCollectionsDAO.insertItems(collection);
              collectionsToRespond.add(collection);
          }
          System.out.println("Upload Finished");

          String response = gson.toJson(collections); // dateSync 에 도장 찍은 컬렉션을 다시 앱으로 보냄
          System.out.println("RESPONSE : " + response);
          httpServer.respond200(httpExchange, response);
      }


/*
      String requestBody = httpServer.readInputStream(httpExchange.getRequestBody());
      System.out.println("Raw Http Body: " + requestBody);

      List<Collection> collections = gson.fromJson(requestBody, new TypeToken<List<Collection>>(){}.getType());
      System.out.println(collections);
      System.out.println("Collection");
      for(Collection collection : collections) {
          System.out.println(collection.getFront());
      }

      Collection collection = gson.fromJson(requestBody, Collection.class);

      akoreanDAO.insertUser(newUser);

      // return the new user as json
      String response = gson.toJson(newUser);
      System.out.println("RESPONSE : " + response);
      httpServer.respond200(httpExchange, response);

 */
  }
}
