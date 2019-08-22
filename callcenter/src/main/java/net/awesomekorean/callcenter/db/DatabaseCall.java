package net.awesomekorean.callcenter.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseCall<T> {

  public T withConnection(Connection connection) throws SQLException;

}
