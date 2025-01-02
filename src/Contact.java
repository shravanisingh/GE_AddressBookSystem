public class Contact {
    String firstName;
    String lastName;
    String city;
    String state;
    String email;
    int phone;
    int zip;

    public Contact(String firstName, String lastName, String city, String state, String email, int phone, int zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phone = phone;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s, City: %s, State: %s, Email: %s, Phone: %d, ZIP: %d",
                firstName, lastName, city, state, email, phone, zip);
    }
}