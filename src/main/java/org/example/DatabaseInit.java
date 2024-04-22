package org.example;

import java.util.List;

public class DatabaseInit {
    private boolean uuid = true;
    private String name_of_table;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String id_mother;
    private String id_father;

    public DatabaseInit() {
    }

    public DatabaseInit(String first_name, String last_name, String date_of_birth, String id_mother, String id_father) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.id_mother = id_mother;
        this.id_father = id_father;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        if (first_name.isEmpty() || first_name.isBlank()) {
            this.first_name = "first_name";
        } else {
            this.first_name = first_name;
        }
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        if (last_name.isEmpty() || last_name.isBlank()) {
            this.last_name = "last_name";
        } else {
            this.last_name = last_name;
        }
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        if (date_of_birth.isEmpty() || last_name.isBlank()) {
            this.date_of_birth = "date_of_birth";
        } else {
            this.date_of_birth = date_of_birth;
        }
    }

    public String getId_mother() {
        return id_mother;
    }

    public void setId_mother(String id_mother) {
        if (id_mother.isEmpty() || id_mother.isBlank()) {
            this.id_mother = "id_mother";
        } else {
            this.id_mother = id_mother;
        }
    }

    public String getId_father() {
        return id_father;
    }

    public void setId_father(String id_father) {
        if (id_father.isEmpty() || id_father.isBlank()) {
            this.id_father = "id_father";
        } else {
            this.id_father = id_father;
        }
    }

    public String createTable() {
        StringBuilder createTable = new StringBuilder();
        createTable.append("CREATE TABLE %s (%s, %s VARCHAR(255), %s VARCHAR(255), %s DATE, %s, %s); \n".formatted(getName_of_table(), uuid ? "uuid VARCHAR(36) PRIMARY KEY" : "id INTEGER PRIMARY KEY", first_name, last_name, date_of_birth, uuid ? id_father +" VARCHAR(36)" : id_father+" INTEGER", uuid ? id_mother+" VARCHAR(36)" : id_mother+" INTEGER"));
        createTable.append("ALTER TABLE %s ADD FOREIGN KEY (%s) REFERENCES %s(%s); \n".formatted(getName_of_table(), id_mother, getName_of_table(), uuid ? "uuid" : "id"));
        createTable.append("ALTER TABLE %s ADD FOREIGN KEY (%s) REFERENCES %s(%s); \n".formatted(getName_of_table(), id_father, getName_of_table(), uuid ? "uuid" : "id"));
        return createTable.toString();
    }

    public String returnInsertSQL(List<PeopleDTO> peopleDTOList) {
        StringBuilder insertSQL = new StringBuilder();
        for (PeopleDTO peopleDTO : peopleDTOList) {
            if (isUuid()) {
                insertSQL.append("INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES ('%s','%s','%s','%s',%s,%s); \n".formatted(getName_of_table(), uuid ? "uuid" : "id", first_name, last_name, date_of_birth, id_mother, id_father, peopleDTO.getUuid(), peopleDTO.getFirst_name(), peopleDTO.getLast_name(), peopleDTO.getDate_of_birth(), peopleDTO.getId_mother() == null ? "null" : "'"+peopleDTO.getId_mother()+"'", peopleDTO.getId_father() == null ? "null" : "'"+peopleDTO.getId_father()+"'"));
            } else {
                insertSQL.append("INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (%s,'%s','%s','%s',%s,%s); \n".formatted(getName_of_table(), uuid ? "uuid" : "id", first_name, last_name, date_of_birth, id_mother, id_father, peopleDTO.getUuid(), peopleDTO.getFirst_name(), peopleDTO.getLast_name(), peopleDTO.getDate_of_birth(), peopleDTO.getId_mother() == null ? "null" : peopleDTO.getId_mother(), peopleDTO.getId_father() == null ? "null" : peopleDTO.getId_father()));
            }
        }

        return insertSQL.toString();
    }

    public boolean isUuid() {
        return uuid;
    }

    public void setUuid(boolean uuid) {
        this.uuid = uuid;
    }

    public void setName_of_table(String name_of_table) {
        if (name_of_table.isEmpty() || name_of_table.isBlank()) {
            this.name_of_table = "people";
        } else {
            this.name_of_table = name_of_table;
        }
    }

    public String getName_of_table() {
        return name_of_table;
    }
}
