package org.example;

public class PeopleDTO {
    private String uuid;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String id_mother;
    private String id_father;

    public PeopleDTO() {
    }

    public PeopleDTO(String uuid, String first_name, String last_name, String date_of_birth, String id_mother, String id_father) {
        this.uuid = uuid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.id_mother = id_mother;
        this.id_father = id_father;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getId_mother() {
        return id_mother;
    }

    public void setId_mother(String id_mother) {
        this.id_mother = id_mother;
    }

    public String getId_father() {
        return id_father;
    }

    public void setId_father(String id_father) {
        this.id_father = id_father;
    }

    @Override
    public String toString() {
        return "PeopleDTO{" +
                "uuid='" + uuid + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", id_mother='" + id_mother + '\'' +
                ", id_father='" + id_father + '\'' +
                '}';
    }

    public String returnInsertSQL() {
        return "INSERT INTO osoby(uuid, first_name, last_name, date_of_birth, id_mother, id_father) VALUES ('%s','%s','%s','%s','%s','%s');".formatted(uuid, first_name, last_name, date_of_birth, id_mother, id_father);
    }
}
