package stepImplementation;


import lombok.extern.slf4j.Slf4j;
import models.Owner;
import models.Pet.ApiResponse;
import models.Pet.Body;
import models.Pet.Pet;
import models.PetOwner;
import models.PetType.Type;
import models.Specialty.Specialty;
import models.Vet.GetDBResponse;
import models.Vet.GetVetApiResponse;
import models.Visit.PetVisit;
import models.Visit.DbBody;
import util.World;
import util.database.DbConnection;
import util.database.Queries;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class DatabaseStepImpl {

    DbConnection database = new DbConnection();


    public List<Specialty> getAllSpecialities() {


        List<Specialty> specialities = new ArrayList<>();

        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {

            ResultSet resultSet = statement.executeQuery(Queries.SELECT_ALL_FROM_SPECIALITY);

            while (resultSet.next()) {

                Specialty speciality = Specialty.builder()
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

    public List<Type> getAllTypes() {


        List<Type> types = new ArrayList<>();

        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {

            ResultSet resultSet = statement.executeQuery(Queries.SELECT_ALL_FROM_TYPES);

            while (resultSet.next()) {

                Type type = Type.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();

                types.add(type);
            }
        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
        return types;
    }

    public List<ApiResponse> getAllPets() {
        List<ApiResponse> pets = new ArrayList<>();

        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {

            ResultSet resultSet = statement.executeQuery(Queries.SELECT_ALL_FROM_PETS_AND_VISITS);

            while (resultSet.next()) {
                List<PetVisit> visits = new ArrayList<>();
                ApiResponse pet = new ApiResponse();
                PetVisit visit = new PetVisit();
                int owner_id = resultSet.getInt("owner_id");
                int type_id = resultSet.getInt("type_id");
                int visit_id = resultSet.getInt("visit_id");
                String date = resultSet.getDate("birth_date").toString();
                String name = resultSet.getString("name");
                if (visit_id != 0) {
                    visit.setId(visit_id);
                    visit.setDescription(resultSet.getString("description"));
                    visit.setDate(resultSet.getDate("visit_date").toString());
                    visits.add(visit);
                }


                int current_id = resultSet.getInt("id");

                boolean found = false;

                for (ApiResponse petTest : pets) {
                    if (petTest.getId() == current_id) {
                        visits.addAll(petTest.getVisits());
                        petTest.setVisits(visits);
                        found = true;
                    }

                }
                if (!found) {

                    Type petType = new Type();
                    if (type_id != 0) {
                        petType.setId(type_id);
                        petType.setName(resultSet.getString("type_name"));
                    }


                    PetOwner petOwner = new PetOwner();
                    if (owner_id != 0) {
                        petOwner.setId(String.valueOf(owner_id));
                        petOwner.setFirstName(resultSet.getString("first_name"));
                        petOwner.setFirstName(resultSet.getString("last_name"));
                        petOwner.setAddress(resultSet.getString("address"));
                        petOwner.setCity(resultSet.getString("city"));
                        petOwner.setTelephone(resultSet.getString("telephone"));
                    }

                    pet.setId(current_id);
                    pet.setName(name);
                    pet.setBirthDate(date);
                    pet.setOwner(petOwner);
                    pet.setType(petType);


                    pet.setVisits(visits);
                    pets.add(pet);
                }


            }
        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

        return pets;
    }

    public List<models.Vet.GetVetApiResponse> getAllVets() {
        List<GetVetApiResponse> vets = new ArrayList<>();

        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {

            ResultSet resultSet = statement.executeQuery(Queries.SELECT_ALL_FROM_TRIPLE_JOIN);

            while (resultSet.next()) {
                List<Specialty> specialties = new ArrayList<>();
                GetVetApiResponse vet = new GetVetApiResponse();
                Specialty specialty = new Specialty();
                int specialty_id = resultSet.getInt("specialty_id");
                if (specialty_id != 0) {
                    specialty.setId(specialty_id);
                    specialty.setName(resultSet.getString("name"));
                    specialties.add(specialty);
                }


                int current_id = resultSet.getInt("id");

                boolean found = false;

                for (GetVetApiResponse vetTest : vets) {
                    if (vetTest.getId() == current_id) {
                        specialties.addAll(vetTest.getSpecialties());
                        vetTest.setSpecialties(specialties);
                        found = true;
                    }

                }
                if (!found) {

                    vet.setId(current_id);
                    vet.setFirstName(resultSet.getString("first_name"));
                    vet.setLastName(resultSet.getString("last_name"));
                    vet.setSpecialties(specialties);
                    vets.add(vet);
                }


            }
        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

        return vets;
    }

    public Specialty getSpecialtyById(Integer id) {

        Specialty specialty = Specialty.builder().build();


        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {
            String query = Queries.SELECT_ALL_FROM_SPECIALITY + " " + Queries.GET_BY_ID + id.toString();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next())
                specialty.setId(resultSet.getInt("id"));
            specialty.setName(resultSet.getString("name"));


        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
        return specialty;
    }

    public models.Vet.GetDBResponse getVetById(Integer id) {

        models.Vet.GetDBResponse vet = models.Vet.GetDBResponse.builder().build();


        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {
            String query = Queries.SELECT_ALL_FROM_VETS + " " + Queries.GET_BY_ID + id.toString();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next())
                vet.setId(resultSet.getInt("id"));
            vet.setFirstName(resultSet.getString("first_name"));
            vet.setLastName(resultSet.getString("last_name"));


        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
        return vet;
    }

    public Type getTypeById(Integer id) {
        Type type = Type.builder().build();


        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {
            String query = Queries.SELECT_ALL_FROM_TYPES + " " + Queries.GET_BY_ID + id.toString();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next())
                type.setId(resultSet.getInt("id"));
            type.setName(resultSet.getString("name"));


        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
        return type;
    }

    public DbBody getVisitById(Integer id) {
        DbBody visit = DbBody.builder().build();


        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {
            String query = Queries.SELECT_ALL_FROM_VISITS + " " + Queries.GET_BY_ID + id.toString();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                visit.setId(resultSet.getInt("id"));
                visit.setDescription(resultSet.getString("name"));
                visit.setDate(resultSet.getDate("date").toString());
                visit.setPetId(resultSet.getInt("pet_id"));
            }


        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
        return visit;
    }

    public Body getPetById(Integer id) {
        Body pet = Body.builder().build();


        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {
            String query = Queries.SELECT_ALL_FROM_PETS_AND_VISITS + " " + Queries.GET_BY_ID + id.toString();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next())
                pet.setId(resultSet.getInt("id"));
            pet.setName(resultSet.getString("name"));
            pet.setBirthDate(resultSet.getString("birth_date"));
            pet.setOwnerId(resultSet.getInt("owner_id"));
            pet.setTypeId(resultSet.getInt("type_id"));


        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
        return pet;
    }

    public Owner getOwnerById(Integer id) {
        Owner owner = Owner.builder().build();


        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {
            String query = Queries.SELECT_ALL_FROM_OWNERS + " " + Queries.GET_BY_ID + id.toString();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                owner.setId(String.valueOf(resultSet.getInt("id")));
                owner.setFirstName(resultSet.getString("first_name"));
                owner.setLastName(resultSet.getString("last_name"));
            }


        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
        return owner;
    }

    public void getSpecialtyIdByName(String name) {

        Specialty specialty = Specialty.builder().build();

        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.SELECT_ALL_FROM_SPECIALITY + " " + Queries.GET_BY_NAME)
        ) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            specialty.setId(resultSet.getInt("id"));
            specialty.setName(resultSet.getString("name"));

        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

        World.setSpecialtyResponse(specialty);

    }

    public void getVetIdByLastName(String name) {

        models.Vet.GetDBResponse vet = models.Vet.GetDBResponse.builder().build();

        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.SELECT_ALL_FROM_VETS + " " + Queries.GET_BY_FIRST_NAME)
        ) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            vet.setId(resultSet.getInt("id"));
            vet.setFirstName(resultSet.getString("first_name"));

        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

        World.setVetResponse(vet);

    }
    public void getOwnerIdByFirstName(String name) {
        Owner owner = Owner.builder().build();

        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.SELECT_ALL_FROM_OWNERS + " " + Queries.GET_BY_FIRST_NAME)
        ) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            owner.setFirstName(resultSet.getString("first_name"));
            owner.setTelephone(resultSet.getString("telephone"));
            owner.setLastName(resultSet.getString("last_name"));
            owner.setId(String.valueOf(resultSet.getInt("id")));
            owner.setAddress(resultSet.getString("address"));
            owner.setCity(resultSet.getString("city"));


        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

        World.setOwnerResponse(owner);
    }

    public void getTypeIdByName(String name) {

        Type type = Type.builder().build();

        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.SELECT_ALL_FROM_TYPES + " " + Queries.GET_BY_NAME)
        ) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                type.setId(resultSet.getInt("id"));
                type.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

        World.setTypeResponse(type);

    }

    public void getVisitIdByDescription(String description) {
        DbBody visit = DbBody.builder().build();

        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.SELECT_ALL_FROM_VISITS + " " + Queries.GET_BY_DESCTIPTION)
        ) {
            preparedStatement.setString(1, description);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                visit.setId(resultSet.getInt("id"));
                visit.setDescription(resultSet.getString("description"));
                visit.setPetId(resultSet.getInt("pet_id"));
                visit.setDate(resultSet.getDate("date").toString());
            }

        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

        World.setVisitResponse(visit);
    }

    public void getPetIdByName(String name) {

        Pet vet = Pet.builder().build();

        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.SELECT_ALL_FROM_PETS + " " + Queries.GET_BY_NAME)
        ) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            vet.setId(resultSet.getInt("id"));
            vet.setName(resultSet.getString("name"));
            vet.setBirthDate(resultSet.getString("birth_date"));
            vet.setOwner(resultSet.getInt("owner_id"));
            Type type = new Type();
            type.setId(resultSet.getInt("type_id"));
            vet.setType(type);


        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

        World.setPetResponse(vet);

    }

    public int getMaxId(String table) {
        int id = 0;
        try (
                Statement statement = database.connectToDatabase().createStatement()
        ) {
            String query = Queries.GET_MAX_ID + table;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
        return id;
    }

    public void deleteSpecialities(List<String> specialities) {
        final String DELETE_RECORDS_FROM_SPECIALITY = Queries.createDelete("specialties", "name", specialities.size());

        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        DELETE_RECORDS_FROM_SPECIALITY)
        ) {
            for (int i = 0; i < specialities.size(); i++)
                preparedStatement.setString(i + 1, specialities.get(i));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

    }

    public void deleteFromTabe(String tableName, List<String> names) {

        String parameter;
        switch (tableName) {
            case "specialties":
            case "types":
            case "pets":
                parameter = "name";
                break;
            case "vets":
            case "owners":
                parameter = "first_name";
                break;
            case "visits":
                parameter = "description";
                break;
            default:
                throw new UnsupportedOperationException();
        }
        final String DELETE_RECORDS_FROM_VETS = Queries.createDelete(tableName, parameter, names.size());

        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        DELETE_RECORDS_FROM_VETS)
        ) {
            for (int i = 0; i < names.size(); i++)
                preparedStatement.setString(i + 1, names.get(i));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

    }

    public void addSpecialties(List<String> specialities) {

        for (String speciality : specialities) {

            try (
                    PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                            Queries.ADD_RECORDS_TO_SPECIALITY)
            ) {
                preparedStatement.setString(1, speciality);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                log.error("Database error: ", e);
            }

        }

    }

    public void addVets(List<String> vets) {

        for (String vet : vets) {

            try (
                    PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                            Queries.ADD_RECORDS_TO_VETS)
            ) {
                preparedStatement.setString(1, vet);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                log.error("Database error: ", e);
            }

        }

    }

    public void addOwners(List<String> owners) {
        for (String owner : owners) {

            try (
                    PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                            Queries.ADD_RECORDS_TO_OWNERS)
            ) {
                preparedStatement.setString(1, owner);
                preparedStatement.setString(2, owner);
                preparedStatement.setString(3, "110 W. Liberty St.");
                preparedStatement.setString(4, "Madison");
                preparedStatement.setString(5, "6085558763");
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                log.error("Database error: ", e);
            }

        }
    }

    public void addTypes(List<String> types) {

        for (String type : types) {

            try (
                    PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                            Queries.ADD_RECORDS_TO_TYPES)
            ) {
                preparedStatement.setString(1, type);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                log.error("Database error: ", e);
            }

        }

    }

    public void addPets(List<String> pets) {

        for (String pet : pets) {

            try (
                    PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                            Queries.ADD_RECORDS_TO_PETS)
            ) {
                preparedStatement.setString(1, pet);
                preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                preparedStatement.setInt(3, 1);
                preparedStatement.setInt(4, 1);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                log.error("Database error: ", e);
            }

        }

    }

    public void addVisits(List<String> visits) {
        for (String visit : visits) {

            try (
                    PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                            Queries.ADD_RECORDS_TO_VISITS)
            ) {
                preparedStatement.setInt(1, 2);
                preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                preparedStatement.setString(3, visit);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                log.error("Database error: ", e);
            }

        }
    }



}






