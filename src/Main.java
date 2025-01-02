import java.util.*;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
        Map<String, AddressBook> addressBookMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("Welcome to Multi-Address Book System!");
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add New Address Book");
            System.out.println("2. Select Address Book");
            System.out.println("3. Display All Address Books");
            System.out.println("4. View Persons by City");
            System.out.println("5. View Persons by State");
            System.out.println("6. Count Contacts by City");
            System.out.println("7. Count Contacts by State");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the new Address Book: ");
                    String newBookName = sc.nextLine();
                    if (addressBookMap.containsKey(newBookName)) {
                        System.out.println("An Address Book with this name already exists.");
                    } else {
                        addressBookMap.put(newBookName, new AddressBook());
                        System.out.println("Address Book '" + newBookName + "' created successfully!");
                    }
                    break;
                case 2:
                    System.out.print("Enter the name of the Address Book to select: ");
                    String selectedBookName = sc.nextLine();
                    AddressBook selectedBook = addressBookMap.get(selectedBookName);
                    if (selectedBook == null) {
                        System.out.println("No Address Book found with this name.");
                    } else {
                        manageAddressBook(selectedBook);
                    }
                    break;
                case 3:
                    if (addressBookMap.isEmpty()) {
                        System.out.println("No Address Books available.");
                    } else {
                        System.out.println("Available Address Books:");
                        addressBookMap.keySet().forEach(System.out::println);
                    }
                    break;
                case 4:
                    viewPersonsByCity(addressBookMap);
                    break;
                case 5:
                    viewPersonsByState(addressBookMap);
                    break;
                case 6:
                    countContactsByCity(addressBookMap);
                    break;
                case 7:
                    countContactsByState(addressBookMap);
                    break;
                case 0:
                    System.out.println("Thank you for using the Multi-Address Book System!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);

        sc.close();
    }
    private static void manageAddressBook(AddressBook addressBook) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAddress Book Menu:");
            System.out.println("1. Create Contact");
            System.out.println("2. Display All Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Sort Contacts by Name");
            System.out.println("6. Sort Contacts by City");
            System.out.println("7. Sort Contacts by State");
            System.out.println("8. Sort Contacts by ZIP Code");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addressBook.createContact();
                    break;
                case 2:
                    addressBook.display();
                    break;
                case 3:
                    addressBook.editContact();
                    break;
                case 4:
                    addressBook.deleteContact();
                    break;
                case 5:
                    addressBook.sortByCity();
                    break;
                case 6:
                    addressBook.sortByCity();
                    break;
                case 7:
                    addressBook.sortByState();
                    break;
                case 8:
                    addressBook.sortByZip();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }
    private static void viewPersonsByCity(Map<String, AddressBook> addressBookMap) {
        Map<String, List<Contact>> cityPersonMap = addressBookMap.values().stream()
                .flatMap(addressBook -> addressBook.adBook.stream())
                .collect(Collectors.groupingBy(contact -> contact.city));

        System.out.println("Persons by City:");
        cityPersonMap.forEach((city, contacts) -> {
            System.out.println("\nCity: " + city);
            contacts.forEach(System.out::println);
        });
    }

    private static void viewPersonsByState(Map<String, AddressBook> addressBookMap) {
        Map<String, List<Contact>> statePersonMap = addressBookMap.values().stream()
                .flatMap(addressBook -> addressBook.adBook.stream())
                .collect(Collectors.groupingBy(contact -> contact.state));

        System.out.println("Persons by State:");
        statePersonMap.forEach((state, contacts) -> {
            System.out.println("\nState: " + state);
            contacts.forEach(System.out::println);
        });
    }

    private static void countContactsByCity(Map<String, AddressBook> addressBookMap) {
        Map<String, Long> cityCountMap = addressBookMap.values().stream()
                .flatMap(addressBook -> addressBook.adBook.stream())
                .collect(Collectors.groupingBy(contact -> contact.city, Collectors.counting()));

        System.out.println("Contact Count by City:");
        cityCountMap.forEach((city, count) -> System.out.println("City: " + city + ", Count: " + count));
    }

    private static void countContactsByState(Map<String, AddressBook> addressBookMap) {
        Map<String, Long> stateCountMap = addressBookMap.values().stream()
                .flatMap(addressBook -> addressBook.adBook.stream())
                .collect(Collectors.groupingBy(contact -> contact.state, Collectors.counting()));

        System.out.println("Contact Count by State:");
        stateCountMap.forEach((state, count) -> System.out.println("State: " + state + ", Count: " + count));
    }
}