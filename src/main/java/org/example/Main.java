package org.example;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final List<PeopleDTO> peopleDTOS = new ArrayList<>();
    private static boolean running = true;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Scanner id_scanner = new Scanner(System.in);
    private static final DatabaseInit databaseInit = new DatabaseInit();

    private static int getUUIDOfPerson(String who) {
        System.out.println(who);
        String id_parent = id_scanner.nextLine();
        if (!id_parent.isEmpty()) {
            boolean father_added = false;
            while ((Integer.parseInt(id_parent) >= 0 || Integer.parseInt(id_parent) < peopleDTOS.size()) && !father_added) {
                father_added = true;
            }
            return Integer.parseInt(id_parent);
        }
        return -1;
    }

    private static void InitDB(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pouzivat UUID (priklad UUID: 550e8400-e29b-41d4-a716-446655440000) [A/n]");
        databaseInit.setUuid(!scanner.nextLine().equalsIgnoreCase("n"));
        System.out.println("Napis nazev tabulky (Default: people)");
        databaseInit.setName_of_table(scanner.nextLine().trim());
        System.out.println("Napis nazev sloupce pro krestni jmeno (Default: first_name)");
        databaseInit.setFirst_name(scanner.nextLine().trim());
        System.out.println("Napis nazev sloupce pro prijmeni (Default: last_name)");
        databaseInit.setLast_name(scanner.nextLine().trim());
        System.out.println("Napis nazev sloupce pro datum narozeni (Default: date_of_birth)");
        databaseInit.setDate_of_birth(scanner.nextLine().trim());
        System.out.println("Napis nazev sloupce pro id matky (Default: id_mother)");
        databaseInit.setId_mother(scanner.nextLine().trim());
        System.out.println("Napis nazev sloupce pro id otce (Default: id_father)");
        databaseInit.setId_father(scanner.nextLine().trim());
    }

    private static PeopleDTO person1() {

        PeopleDTO person1 = new PeopleDTO();

        person1.setUuid(databaseInit.isUuid() ? UUID.randomUUID().toString() : Integer.toString(peopleDTOS.size()+1));
        System.out.println(databaseInit.getFirst_name());
        String first_name = scanner.nextLine();
        while (first_name.isEmpty()) {
            System.out.println("Krestni jmeno nesmi byt prazdne");
            first_name = scanner.nextLine();
        }
        person1.setFirst_name(first_name);
        System.out.println(databaseInit.getLast_name());
        String last_name = scanner.nextLine();
        while (last_name.isEmpty()) {
            System.out.println("Prijmeni nesmi byt prazdne");
            last_name = scanner.nextLine();
        }
        person1.setLast_name(last_name);
        System.out.println(databaseInit.getDate_of_birth() + " (prosim dodrz tento format: YYYY-MM-DD)");
        String date_of_birth = scanner.nextLine();
        while (date_of_birth.isEmpty()) {
            System.out.println("Datum narozeni nesmi byt prazdne");
            date_of_birth = scanner.nextLine();
        }
        person1.setDate_of_birth(date_of_birth);


        if (!peopleDTOS.isEmpty()) {
            int n = 0;
            for (PeopleDTO peopleDTO : peopleDTOS) {
                System.out.println(
                        n + " | " +
                                peopleDTO.toString()
                );
                n++;
            }

            int id = getUUIDOfPerson(databaseInit.getId_father());
            if (id != -1) {
                person1.setId_father(peopleDTOS.get(id).getUuid());
            } else {
                person1.setId_father(null);
            }

            id = getUUIDOfPerson(databaseInit.getId_mother());
            if (id != -1) {
                person1.setId_mother(peopleDTOS.get(id).getUuid());
            } else {
                person1.setId_mother(null);
            }
        }

        boolean editing = true;
        while (editing) {
            System.out.println(
                    person1
            );
            System.out.println("Upravit? [N/a]");
            if (scanner.nextLine().equalsIgnoreCase("a")) {
                Scanner select_scanner = new Scanner(System.in);
                System.out.println("1) Krestni Jmeno, 2) Prijmeni 3) Datum narozeni 4) Matku 5) Otce (cokoliv jineho pro ukonceni editace)");
                switch (select_scanner.nextInt()) {
                    case 1:
                        System.out.println("Krestni jmeno " + person1.getFirst_name());
                        person1.setFirst_name(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Prijmeni " + person1.getLast_name());
                        person1.setLast_name(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Datum narozeni " + person1.getDate_of_birth());
                        person1.setDate_of_birth(scanner.nextLine());
                        break;
                    case 4:
                        String nameOfMother = "";
                        if (!peopleDTOS.isEmpty()) {
                            int n = 0;
                            for (PeopleDTO peopleDTO : peopleDTOS) {
                                System.out.println(
                                        n + " | " +
                                                peopleDTO.toString()
                                );
                                if (peopleDTO.getId_father() != null) {
                                    if (peopleDTO.getId_father().equals(person1.getUuid())) {
                                        nameOfMother = "(" + peopleDTO.getFirst_name() + " " + peopleDTO.getLast_name() + ")";
                                    }
                                }
                                n++;
                            }

                            int id = getUUIDOfPerson("Matka" + nameOfMother);
                            if (id != -1) {
                                person1.setId_mother(peopleDTOS.get(id).getUuid());
                            } else {
                                person1.setId_mother(null);
                            }
                        }
                        break;
                    case 5:
                        String nameOfFather = "";
                        if (!peopleDTOS.isEmpty()) {
                            int n = 0;
                            for (PeopleDTO peopleDTO : peopleDTOS) {
                                System.out.println(
                                        n + " | " +
                                                peopleDTO.toString()
                                );
                                if (peopleDTO.getId_father() != null) {
                                    if (peopleDTO.getId_father().equals(person1.getUuid())) {
                                        nameOfFather = "(" + peopleDTO.getFirst_name() + " " + peopleDTO.getLast_name() + ")";
                                    }
                                }
                                n++;
                            }

                            int id = getUUIDOfPerson("Otec" + nameOfFather);
                            if (id != -1) {
                                person1.setId_father(peopleDTOS.get(id).getUuid());
                            } else {
                                person1.setId_father(null);
                            }
                        }
                        break;
                    default:
                        editing = false;
                        break;
                }
            } else {
                editing = false;
            }
        }

        return person1;
    }

    public static void main(String[] args) {
        InitDB();

        while (running) {

            peopleDTOS.add(person1());

            System.out.println("Pridat dalsiho cloveka? [A/n]");
            if (scanner.nextLine().equalsIgnoreCase("n")) {
                running = false;
            }
        }

        try {
            FileWriter myWriter = new FileWriter(databaseInit.getName_of_table()+".sql");
            myWriter.write(databaseInit.createTable() + "\n" + databaseInit.returnInsertSQL(peopleDTOS));
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("SQL se ulozilo do '" + databaseInit.getName_of_table()+".sql'");
        System.out.println(databaseInit.createTable());
        System.out.println(databaseInit.returnInsertSQL(peopleDTOS));
    }
}
