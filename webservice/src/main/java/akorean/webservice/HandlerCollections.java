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

      if(requestMethod.equalsIgnoreCase("GET")) {

          // 다운로드 할 아이템 찾기
          String[] urlSplit = url.toString().split("/");
          String dateLastSync = urlSplit[2];
          System.out.println("dateLastSync: " + dateLastSync);
          List<Map<String, String>> found = akoreanCollectionsDAO.getDownloadItems(dateLastSync);

          if(found != null) {
              String response = gson.toJson(found);
              System.out.println("RESPONSE : " + response);
              httpServer.respond200(httpExchange, response);
          } else {
              httpServer.respond404(httpExchange);
          }

      }else if (requestMethod.equalsIgnoreCase("POST")) {

          String requestBody = httpServer.readInputStream(httpExchange.getRequestBody());
          System.out.println("Raw Http Body: " + requestBody);

          List<Collection> collections = gson.fromJson(requestBody, new TypeToken<List<Collection>>() {
          }.getType());

          // 업로드 하기
          for (Collection collection : collections) {
              akoreanCollectionsDAO.getItemByGuid(collection);
          }
          System.out.println("UPLOAD Finished");

          String response = gson.toJson(collections);
          System.out.println("RESPONSE : " + response);
          httpServer.respond200(httpExchange, response);
      }
  }
}
