import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
    List<Contact> adBook = new ArrayList<>();

    public void display() {
        if (adBook.isEmpty()) {
            System.out.println("Address book is empty.");
        } else {
            for (Contact contact : adBook) {
                System.out.println(contact);
            }
        }
    }

    public void createContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String name = sc.next();
        System.out.println("Enter Last Name:");
        String lname = sc.next();
        System.out.println("Enter City:");
        String city = sc.next();
        System.out.println("Enter State:");
        String state = sc.next();
        System.out.println("Enter Email:");
        String email = sc.next();
        System.out.println("Enter Phone:");
        int phone = sc.nextInt();
        System.out.println("Enter ZIP:");
        int zip = sc.nextInt();

        Contact c1 = new Contact(name, lname, city, state, email, phone, zip);
        adBook.add(c1);
        System.out.println("Contact added successfully!");
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Contact contact : adBook) {
                writer.write(contact.toFileString());
                writer.newLine();
            }
            System.out.println("Address book saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while saving address book: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            adBook.clear(); // Clear current address book
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Contact contact = new Contact(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        Integer.parseInt(data[5]),
                        Integer.parseInt(data[6])
                );
                adBook.add(contact);
            }
            System.out.println("Address book loaded from file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while loading address book: " + e.getMessage());
        }
    }
}