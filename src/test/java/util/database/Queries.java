package util.database;


import lombok.Getter;
import lombok.Setter;

public class Queries {

    public final static String SELECT_ALL_FROM_SPECIALITY = "SELECT * FROM specialties";

    public final static String SELECT_ALL_FROM_OWNERS = "SELECT * FROM owners";

    public final static String SELECT_ALL_FROM_PETS = "SELECT * FROM pets";
    public static final String SELECT_ALL_FROM_VISITS = "SELECT * FROM visits";

    public static final String SELECT_ALL_FROM_PETS_AND_VISITS = "select p.id as id, p.\"name\" as \"name\" , p.birth_date as birth_date , p.type_id as type_id , p.owner_id as owner_id , o.first_name as first_name, o.last_name as last_name, o.address as address,o.city as city, o.telephone as telephone,v.id as visit_id,v.visit_date as visit_date ,v.description as description,t.\"name\" as type_name from pets p left join visits v on p.id =v.pet_id left join owners o on p.owner_id = o.id  left join types t on t.id = p.type_id ;";
    public final static String SELECT_ALL_FROM_TYPES = "SELECT * FROM types";

    public final static String SELECT_ALL_FROM_TRIPLE_JOIN="select  v.id, v.first_name , v.last_name , s.id as specialty_id ,s.\"name\" from vets v left join vet_specialties vs on v.id =vs.vet_id left join specialties s on s.id=vs.specialty_id order by v.id asc;";

    public final static String SELECT_ALL_FROM_VETS = "SELECT * FROM vets";

    public final static String GET_MAX_ID = "select max(id) as id from ";

    public final static String GET_BY_ID = "Where id = ";

    public final static String GET_BY_NAME = "Where name = ?";

    public final static String GET_BY_DESCTIPTION = "Where description = ?";

    public final static String GET_BY_FIRST_NAME = "Where first_name=?";

    public final static String ADD_RECORDS_TO_SPECIALITY = "INSERT into specialties (name) values (?)";
    public static final String ADD_RECORDS_TO_VETS = "INSERT into vets (first_name) values (?)";
    public static final String ADD_RECORDS_TO_TYPES = "INSERT into types (name) values (?)";
    public static final String ADD_RECORDS_TO_PETS = "INSERT into pets (name,birth_date,type_id,owner_id) values (?,?,?,?)";
    public static final String ADD_RECORDS_TO_VISITS = "INSERT into visits (pet_id,visit_date,description) values (?,?,?)";
    public static final String ADD_RECORDS_TO_OWNERS = "INSERT into owners (first_name,last_name,address,city,telephone) values (?,?,?,?,?)";;


    public static String createDelete(String table, String parameter, int size) {

        StringBuilder query = new StringBuilder("DELETE FROM " + table + " where " + parameter + " in (");

        for (int i = 0; i < size; i++) {
            if (i + 1 != size)
                query.append("?,");
            else
                query.append("?)");
        }

        return query.toString();

    }


}
