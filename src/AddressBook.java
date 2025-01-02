import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public void sortByCity() {
        List<Contact> sortedByCity = adBook.stream()
                .sorted(Comparator.comparing(contact -> contact.city))
                .collect(Collectors.toList());

        System.out.println("Contacts sorted by City:");
        sortedByCity.forEach(System.out::println);
    }

    public void sortByState() {
        List<Contact> sortedByState = adBook.stream()
                .sorted(Comparator.comparing(contact -> contact.state))
                .collect(Collectors.toList());

        System.out.println("Contacts sorted by State:");
        sortedByState.forEach(System.out::println);
    }

    public void sortByZip() {
        List<Contact> sortedByZip = adBook.stream()
                .sorted(Comparator.comparingInt(contact -> contact.zip))
                .collect(Collectors.toList());

        System.out.println("Contacts sorted by ZIP Code:");
        sortedByZip.forEach(System.out::println);
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

    public void editContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the First Name of the contact to edit:");
        String name = sc.next();

        for (Contact contact : adBook) {
            if (contact.firstName.equalsIgnoreCase(name)) {
                System.out.println("Contact found: " + contact);
                System.out.println("Enter new details:");
                System.out.print("Enter Last Name: ");
                contact.lastName = sc.next();
                System.out.print("Enter City: ");
                contact.city = sc.next();
                System.out.print("Enter State: ");
                contact.state = sc.next();
                System.out.print("Enter Email: ");
                contact.email = sc.next();
                System.out.print("Enter Phone: ");
                contact.phone = sc.nextInt();
                System.out.print("Enter ZIP: ");
                contact.zip = sc.nextInt();

                System.out.println("Contact updated successfully!");
                return;
            }
        }
        System.out.println("Contact with the given name not found.");
    }

    public void deleteContact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the First Name of the contact to delete:");
        String name = sc.next();

        for (Contact contact : adBook) {
            if (contact.firstName.equalsIgnoreCase(name)) {
                adBook.remove(contact);
                System.out.println("Contact deleted successfully!");
                return;
            }
        }
        System.out.println("Contact with the given name not found.");
    }
}