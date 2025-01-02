import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AddressBook {
    List<Contact> adBook = new ArrayList<>();
    public void display() {
        if (adBook.isEmpty()) {
            System.out.println("Address book is empty.");
        } else {
            adBook.forEach(System.out::println);
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
        Contact newContact = new Contact(name, lname, city, state, email, phone, zip);
        boolean isDuplicate = adBook.stream().anyMatch(contact -> contact.equals(newContact));
        if (isDuplicate) {
            System.out.println("Duplicate contact found. Contact not added.");
        } else {
            adBook.add(newContact);
            System.out.println("Contact added successfully!");
        }
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
        Contact toRemove = adBook.stream()
                .filter(contact -> contact.firstName.equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (toRemove != null) {
            adBook.remove(toRemove);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact with the given name not found.");
        }
    }
}