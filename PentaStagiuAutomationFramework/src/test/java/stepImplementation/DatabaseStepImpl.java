package stepImplementation;


import lombok.extern.slf4j.Slf4j;
import models.Owner;
import models.Speciality;
import util.database.DbConnection;
import util.database.DbTables;
import util.database.Queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class DatabaseStepImpl {

  DbConnection database = new DbConnection();


  public List<Speciality> getAllSpecialities () {

    String table = DbTables.SPECIALITIES.getTableName();

    List<Speciality> specialities = new ArrayList<>();

    try (
            Statement statement = database.connectToDatabase().createStatement();
    ) {

      //preparedStatement.setString(1, "specialities");

      ResultSet resultSet = statement.executeQuery(Queries.SELECT_ALL_FROM_TABLE);

      while (resultSet.next()) {

        Speciality speciality = Speciality.builder()
            .id(resultSet.getInt("id"))
            .name(resultSet.getString("name"))
            .build();

        specialities.add(speciality);
      }
    } catch (SQLException e) {
      log.error("Database error: ", e);
    }

    return specialities;
  }


  public void deleteSpecialities (List<String> specialities) {

    try (
        PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
            Queries.DELETE_RECORDS_FROM_SPECIALITY)
    ) {

      preparedStatement.setString(1, specialities.get(0));
      preparedStatement.setString(2, specialities.get(1));

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      log.error("Database error: ", e);
    }

  }

  public void addSpecialities(String name){
    try (
            PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                    Queries.INSERT_RECORDS_IN_SPECIALITY)
    ) {

      preparedStatement.setString(1, name);

      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      log.error("Database error: ", e);
    }

  }

  public List<Owner> getAllOwners () {

    String table = DbTables.DbOwnersTables.OWNERS.getTableOwnersName();

    List<Owner> owners = new ArrayList<>();

    try (
            Statement statement = database.connectToDatabase().createStatement();
    ) {


      ResultSet resultSet = statement.executeQuery(Queries.SELECT_ALL_OWNERS_FROM_TABLE);

      while (resultSet.next()) {

        Owner owner = Owner.builder()
                .id(resultSet.getString("id"))
                .firstName(resultSet.getString("first_name"))
                .address(resultSet.getString("address"))
                .city(resultSet.getString("city"))
                .telephone(resultSet.getString("telephone"))
                .build();

        owners.add(owner);
      }
    } catch (SQLException e) {
      log.error("Database error: ", e);
    }

    return owners;
  }
}

