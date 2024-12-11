import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AddressBook a1 = new AddressBook();
        int choice;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Address Book System!");
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Create Contact");
            System.out.println("2. Display All Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 : a1.createContact();
                case 2 : a1.display();
                case 3 :a1.editContact();
                case 4 : a1.deleteContact();
                case 0 :System.out.println("Thank you for using Address Book System!");
                default :System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}