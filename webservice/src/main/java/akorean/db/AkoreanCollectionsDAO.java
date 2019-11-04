package akorean.db;

import akorean.webservice.Collection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AkoreanCollectionsDAO {

  DatabaseManager databaseManager;

  public AkoreanCollectionsDAO(DatabaseManager databaseManager) {
    this.databaseManager = databaseManager;
  }

  // 현재 날짜와 마지막 동기화 날짜 사이에 있는 컬렉션 가져오기
  public List<Map<String, String>> getDownloadItems(String dateLastSync) {
    try {
      return databaseManager.execute(new DatabaseCall<List<Map<String, String>>>() {

        String sql = "select * from COLLECTIONS where DATE_EDIT > '" + dateLastSync + "'";

        @Override
        public List<Map<String, String>> withConnection(Connection connection) throws SQLException {

          System.out.println(sql);
          System.out.println("Attempting to find collections for downloading");

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
      System.out.println("ERROR: SQL Exception");
      System.out.println(e);
      return null;
    }
  }


  public Map<String, String> getItemByGuid(Collection collection) {
    try {
      return databaseManager.execute(new DatabaseCall<Map<String, String>>() {

        String guid = collection.getGuid();
        String sql = "select * from COLLECTIONS where GUID = '" + guid + "'";

        @Override
        public Map<String, String> withConnection(Connection connection) throws SQLException {

          System.out.println(sql);
          System.out.println("Attempting to find item by GUID");

          Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(sql);
          Map<String, String> result = new HashMap<>();

          while(rs.next()) {
            result = resultSetToMapCollection(rs);
          }

          statement.close();

          // 해당 GUID 의 아이템이 없으면 INSERT
          if(result.isEmpty()) {
            System.out.println("Couldn't find item with GUID. Trying to INSERT");
            insertItem(collection);
            System.out.println("Item INSERTED : " + collection.getFront());
            return null;

            // 해당 아이템이 있으면 dateEdit 비교하고 UPDATE
          }else {
            String stringDateCollection = collection.getDateEdit();
            String stringDateResult = result.get("dateEdit");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
              Date dateEditRoom = format.parse(stringDateCollection);
              Date dateEditDB = format.parse(stringDateResult);

              if(dateEditRoom.getTime() > dateEditDB.getTime()) {
                updateItems(collection);
              }
            } catch (ParseException e) {
              e.printStackTrace();
            }
          }
          return null;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: SQL Exception");
      System.out.println(e);
      return null;
    }
  }

  // 컬렉션들 Insert 하기
  public Collection insertItem(Collection collection) {

    try {
      return databaseManager.execute(new DatabaseCall<Collection>() {

        int userId = collection.getUserId();
        String guid = collection.getGuid();
        String front = collection.getFront();
        String back = collection.getBack();
        String audio = collection.getAudio();
        String dateNew = collection.getDateNew();
        String dateEdit = collection.getDateEdit();
        int deleted = collection.getDeleted();
        int itemId = collection.getItemId();


        String sql = "insert into COLLECTIONS (USER_ID, GUID, FRONT, BACK, AUDIO, DATE_NEW, DATE_EDIT, DELETED, ITEM_ID) " +
                "values ('" + userId + "', '" + guid + "', '" + front + "', '" + back + "', '" + audio + "', '" + dateNew + "', '" + dateEdit + "', '" + deleted + "', '" + itemId + "')";

        @Override
        public Collection withConnection(Connection connection) throws SQLException {

          Statement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

          statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
          ResultSet rs = statement.getGeneratedKeys();
          rs.next();

          statement.close();

          return null;
        }
      });
    } catch (SQLException e) {
      System.out.println("ERROR: SQL Exception");
      System.out.println(e);
      return null;
    }
  }


  // 컬렉션들UPDATE 하기
  public Collection updateItems(Collection collection) {

    try {
      return databaseManager.execute(new DatabaseCall<Collection>() {

        String guid = collection.getGuid();
        String front = collection.getFront();
        String back = collection.getBack();
        String audio = collection.getAudio();
        String dateEdit = collection.getDateEdit();
        int deleted = collection.getDeleted();
        int itemId = collection.getItemId();

        String sql = "update COLLECTIONS set FRONT='"+front+"', BACK='"+back+"', AUDIO='"+audio+"', DATE_EDIT='"+dateEdit+"', DELETED='"+deleted+"', ITEM_ID='"+itemId+"' where GUID='"+guid+"'";

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
      System.out.println("ERROR: SQL Exception");
      System.out.println(e);
      return null;
    }
  }


  // ResultSet 을 Map 으로 변경하기 (Collection)
  private Map<String, String> resultSetToMapCollection(ResultSet rs) throws SQLException {
    String userId = rs.getString("USER_ID");
    String guid = rs.getString("GUID");
    String front = rs.getString("FRONT");
    String back = rs.getString("BACK");
    String audio = rs.getString("AUDIO");
    String dateNew = rs.getString("DATE_NEW");
    String dateEdit = rs.getString("DATE_EDIT");
    String deleted = rs.getString("DELETED");
    String itemId = rs.getString("ITEM_ID");

    Map<String, String> result = new HashMap<>();
    result.put("userId", userId);
    result.put("guid", guid);
    result.put("front", front);
    result.put("back", back);
    result.put("audio", audio);
    result.put("dateNew", dateNew);
    result.put("dateEdit", dateEdit);
    result.put("deleted", deleted);
    result.put("itemId", itemId);

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
