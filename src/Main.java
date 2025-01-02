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
            System.out.println("4. Search Person by City or State");
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
                    searchPersonByCityOrState(addressBookMap);
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
                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }

    private static void searchPersonByCityOrState(Map<String, AddressBook> addressBookMap) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSearch Menu:");
        System.out.println("1. Search by City");
        System.out.println("2. Search by State");
        System.out.print("Enter your choice: ");
        int searchChoice = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.print("Enter the name of the City/State to search: ");
        String query = sc.nextLine();

        List<Contact> result = new ArrayList<>();

        switch (searchChoice) {
            case 1: // Search by City
                addressBookMap.values().forEach(addressBook -> {
                    List<Contact> cityContacts = addressBook.adBook.stream()
                            .filter(contact -> contact.city.equalsIgnoreCase(query))
                            .collect(Collectors.toList());
                    result.addAll(cityContacts);
                });
                break;
            case 2: // Search by State
                addressBookMap.values().forEach(addressBook -> {
                    List<Contact> stateContacts = addressBook.adBook.stream()
                            .filter(contact -> contact.state.equalsIgnoreCase(query))
                            .collect(Collectors.toList());
                    result.addAll(stateContacts);
                });
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (result.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Search Results:");
            result.forEach(System.out::println);
        }
    }
}