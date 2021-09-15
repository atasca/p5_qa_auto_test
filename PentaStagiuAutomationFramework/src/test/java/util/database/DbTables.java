package util.database;


import lombok.Getter;


@Getter
public enum DbTables {
  SPECIALITIES("specialities");
  private final String tableName;


  DbTables (String tableName) {

    this.tableName = tableName;
  }

  @Getter
  public enum DbOwnersTables{
    OWNERS("owners");
    private final String tableOwnersName;

    DbOwnersTables(String tableOwnersName) {

      this.tableOwnersName=tableOwnersName;
    }
  }

}
