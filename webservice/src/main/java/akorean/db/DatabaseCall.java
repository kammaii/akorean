package akorean.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseCall<T> {

  T withConnection(Connection connection) throws SQLException;

}

