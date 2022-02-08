package util.database;


import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Slf4j
public class DbConnection {

  private Connection connection;


  public Connection connectToDatabase () {

    try {
      connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/petclinic",
          "postgres",
          "postgress"
      );

    } catch (SQLException e) {
      log.error("Could not get database connection", e);
    }
    return connection;
  }
}
