package pl.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String title;
    private final String email;
    private String group;

    public ContactData(String firstName, String lastName, String title, String email, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.email = email;
        this.group = group;
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

    public String getGroup() {
        return group;
    }
}
