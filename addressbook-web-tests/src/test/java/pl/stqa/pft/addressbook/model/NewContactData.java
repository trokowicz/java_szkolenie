package pl.stqa.pft.addressbook.model;

public class NewContactData {
    private final String firstName;
    private final String lastName;
    private final String title;
    private final String email;

    public NewContactData(String firstName, String lastName, String title, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }
}
