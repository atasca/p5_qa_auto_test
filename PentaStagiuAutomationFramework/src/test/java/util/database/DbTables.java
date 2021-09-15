package util.database;


import lombok.Getter;


@Getter
public enum DbTables {
  SPECIALITIES("specialities");

  private final String tableName;


  DbTables (String tableName) {

    this.tableName = tableName;
  }

}
