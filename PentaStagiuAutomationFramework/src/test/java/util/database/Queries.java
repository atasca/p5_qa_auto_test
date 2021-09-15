package util.database;


public class Queries {

  public final static String SELECT_ALL_FROM_TABLE = "SELECT * FROM TABLE ?";

  public final static String DELETE_RECORDS_FROM_SPECIALITY = "DELETE FROM specialties where name in (?,?)";

  public final static String ADD_RECORDS_TO_SPECIALITY = "INSERT INTO specialities (?,?)";
}
