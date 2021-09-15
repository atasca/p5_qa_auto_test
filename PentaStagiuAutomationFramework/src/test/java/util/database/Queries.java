package util.database;


public class Queries {

  public final static String SELECT_ALL_FROM_TABLE = "SELECT * FROM specialties";

  public final static String SELECT_ALL_OWNERS_FROM_TABLE = "SELECT * FROM owners";

  public final static String DELETE_RECORDS_FROM_SPECIALITY = "DELETE FROM specialties where name in (?,?)";

  public final static String INSERT_RECORDS_IN_SPECIALITY = "INSERT INTO specialties (name) VALUES (?)";



}
