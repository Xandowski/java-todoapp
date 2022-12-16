package todoapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {

  public static final String driverClassName = "com.mysql.cj.jdbc.Driver";
  public static final String connectionUrl = "jdbc:mysql://localhost:3306/todoapp";
  public static final String username = "root";
  public static final String password = "mysqlpw";

  public static Connection getConnection() {
    try {
      Class.forName(driverClassName);
      return DriverManager.getConnection(connectionUrl, username, password);
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException("Error connecting to the database", ex);
    }
  }

  public static void closeConnection(Connection connection) {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException("Error closing connection to the database", ex);
    }
  }

  public static void closeConnection(Connection connection, PreparedStatement statement) {
    try {
      if (connection != null) {
        connection.close();
      }

      if (statement != null) {
        statement.close();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException("Error closing connection to the database", ex);
    }
  }

  public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
    try {
      if (connection != null) {
        connection.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (resultSet != null) {
        resultSet.close();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException("Error closing connection to the database", ex);
    }
  }
}