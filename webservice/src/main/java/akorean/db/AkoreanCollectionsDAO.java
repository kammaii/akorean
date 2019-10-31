package akorean.db;

import akorean.webservice.Collection;
import akorean.webservice.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class AkoreanCollectionsDAO {

  DatabaseManager databaseManager;

  public AkoreanCollectionsDAO(DatabaseManager databaseManager) {
    this.databaseManager = databaseManager;
  }
/*
  // 마지막 아이템의 아이디 불러오기
  public Map<String, String> getLastItemId(String userId) {

    try {
      return databaseManager.execute(new DatabaseCall<Map<String, String>>() {

        String sql = "SELECT ITEM_ID FROM COLLECTIONS WHERE USER_ID = '" + userId + "' ORDER BY ITEM_ID DESC limit 1";

        @Override
        public Map<String, String> withConnection(Connection connection) throws SQLException {

          System.out.println(sql);
          System.out.println("Attempting find collections with the last ITEM_ID of USER_ID " + userId);

          Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          Map<String, String> results = new HashMap<>();

          if(rs.next()) {
            results = resultSetToMapCollection(rs);
          }

          statement.close();
          if(results.size() <= 0) {
            System.out.println("DB COLLECTIONS is empty");
            return null;
          }

          return results;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to get collections with user Id because of SQL Exception");
      System.out.println(e);
      return null;
    }
  }
*/

  // 현재 날짜와 마지막 동기화 날짜 사이에 있는 컬렉션 가져오기
  public List<Map<String, String>> getDownloadItems(String dateLastSync, String dateNow) {
    try {
      return databaseManager.execute(new DatabaseCall<List<Map<String, String>>>() {

        String sql = "select * from COLLECTIONS where DATE_SYNC between '" + dateLastSync + "' and '" + dateNow + "'";

        @Override
        public List<Map<String, String>> withConnection(Connection connection) throws SQLException {

          System.out.println(sql);
          System.out.println("Attempting find collections for downloading");

          Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          List<Map<String, String>> results = new ArrayList<>();

          while(rs.next()) {
            Map<String, String> result = resultSetToMapCollection(rs);
            results.add(result);
          }

          statement.close();
          if(results.size() <= 0) {
            System.out.println("No item to download");
            return null;
          }
          return results;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to insert user because of SQL Exception");
      System.out.println(e);
      return null;
    }
  }

  // 컬렉션들 Insert 하기
  public Collection insertItems(Collection newCollection) {

    try {
      return databaseManager.execute(new DatabaseCall<Collection>() {

        int userId = newCollection.getUserId();
        String guid = newCollection.getGuid();
        String front = newCollection.getFront();
        String back = newCollection.getBack();
        String audio = newCollection.getAudio();
        String dateNew = newCollection.getDateNew();
        String dateEdit = newCollection.getDateEdit();
        String dateSync = newCollection.getDateSync();  // 업로드 할 아이템들에 현재 날짜 도장찍기

        String sql = "insert into COLLECTIONS (USER_ID, GUID, FRONT, BACK, AUDIO, DATE_NEW, DATE_EDIT, DATE_SYNC) " +
                "values ('" + userId + "', '" + guid + "', '" + front + "', '" + back + "', '" + audio + "', '" + dateNew + "', '" + dateEdit + "', '" + dateSync + "')";

        @Override
        public Collection withConnection(Connection connection) throws SQLException {

          System.out.println(sql);

          Statement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

          statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
          ResultSet rs = statement.getGeneratedKeys();
          rs.next();

          statement.close();

          return null;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to insert user because of SQL Exception");
      System.out.println(e);
      return null;
    }
  }

  /*
  // 아이디로 컬렉션 불러오기
  public List<Map<String, String>> getCollectionsById(String userId) {

    try {
      return databaseManager.execute(new DatabaseCall<List<Map<String, String>>>() {

        String sql = "SELECT * FROM COLLECTIONS WHERE ID = "+ userId;

        @Override
        public List<Map<String, String>> withConnection(Connection connection) throws SQLException {

          System.out.println("Attempting find collections with user Id = '" + userId + "'");
          System.out.println(sql);

          Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          List<Map<String, String>> results = new ArrayList<>();

          while(rs.next()) {
            Map<String, String> result = resultSetToMapCollection(rs);
            results.add(result);
          }

          statement.close();
          if(results.size() <= 0) {
            System.out.println("Unable to find collections with user Id= '" + userId + "'");
            return null;
          }

          return results;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: Unable to get collections with user Id because of SQL Exception");
      System.out.println(e);
      return null;
    }
  }
*/

  // ResultSet 을 Map 으로 변경하기 (Collection)
  private Map<String, String> resultSetToMapCollection(ResultSet rs) throws SQLException {
    String guid = rs.getString("GUID");
    String front = rs.getString("FRONT");
    String back = rs.getString("BACK");
    String audio = rs.getString("AUDIO");
    String dateNew = rs.getString("DATE_NEW");
    String dateEdit = rs.getString("DATE_EDIT");
    String dateSync = rs.getString("DATE_SYNC");

    Map<String, String> result = new HashMap<>();
    result.put("guid", guid);
    result.put("front", front);
    result.put("back", back);
    result.put("audio", audio);
    result.put("dateNew", dateNew);
    result.put("dateEdit", dateEdit);
    result.put("dateSync", dateSync);

    System.out.println("Found Collections: " + front);
    return result;
  }
}

/**
 * This code can convert ResultSet to List<Map>
 */
/*
int columnCount = rs.getMetaData().getColumnCount();
            for(int i = 0; i<columnCount; i++) {
        String colName = rs.getMetaData().getColumnName(i);
        String value = rs.getString(colName);
        result.put(colname, value);
        }
        */
