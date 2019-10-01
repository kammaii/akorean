package akorean.webservice;

import akorean.db.AkoreanDAO;
import akorean.db.DatabaseManager;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class AkoreanHttpServer {

  private DatabaseManager databaseManager;
  private AkoreanDAO akoreanDAO;

  /*
   * Constructor Dependency Injection
   *  Spring Framework - is a library that will do dependency injection
   */
  public AkoreanHttpServer(DatabaseManager databaseManager) {
    this.databaseManager = databaseManager;
    this.akoreanDAO = new AkoreanDAO(databaseManager);
  }

  // Java Http Servers
  // Tomcat
  // Jetty
  // Instead of using Tomcat or Jetty, this class will provide an embedded Http Server

  public static void main(String[] args) throws IOException {

    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
    HttpContext context = server.createContext("/users");

    // TODO: move this to config file, or read from command line args
    String serverAddress = "localhost";
    String username = "root";
    String password = "";
    String db = "akorean";
    DatabaseManager databaseManager = new DatabaseManager(serverAddress, username, password, db);
    AkoreanDAO akoreanDAO = new AkoreanDAO(databaseManager);
    AkoreanHttpServer akoreanHttpServer = new AkoreanHttpServer(databaseManager);
    HttpHandler usersHandler = new UsersHandler(akoreanHttpServer, akoreanDAO);
    context.setHandler(usersHandler);

    server.start();
  }

  void respond200(HttpExchange httpExchange, String response) {
    try {
      httpExchange.sendResponseHeaders(200, response.getBytes().length);

      OutputStream os = httpExchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
    catch (IOException e) {
      System.out.println("Error occurred when trying to send response");
      System.out.println(e.getMessage());
    }
  }

  void respond404(HttpExchange httpExchange) {
    String response = "not found";
    try {
      httpExchange.sendResponseHeaders(404, response.getBytes().length);

      OutputStream os = httpExchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
    catch (IOException e) {
      System.out.println("Error occurred when trying to send response");
      System.out.println(e.getMessage());
    }
  }

  String readInputStream(InputStream is) throws IOException {
    StringBuilder results = new StringBuilder();
    try (InputStreamReader reader = new InputStreamReader(is)) {
      char[] buffer = new char[256];
      int read;
      while ((read = reader.read(buffer)) != -1) {
        results.append(buffer, 0, read);
      }
    }
    is.close();
    return results.toString();
  }

  public Map<String, String> splitUrlQuery(URL url) {
    String query = url.getQuery();
    return splitQueryStr(query);
  }

  public Map<String, String> splitQueryStr(String query) {
    Map<String, String> queryParams = new HashMap<>();
    String[] pairs = query.split("&");
    try {
      for (String pair : pairs) {
        int idx = pair.indexOf("=");
        String enc = "UTF-8";
        String key = URLDecoder.decode(pair.substring(0, idx), enc);
        String val = URLDecoder.decode(pair.substring(idx + 1), enc);
        queryParams.put(key,val);
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return queryParams;
  }

}
