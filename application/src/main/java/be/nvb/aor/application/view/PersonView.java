package be.nvb.aor.application.view;

import java.util.ArrayList;
import java.util.List;

public class PersonView {

    private String id;
    private String firstName;
    private String lastName;
    private List<ContactInformationView> contacts = new ArrayList<>();

    public PersonView() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ContactInformationView> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactInformationView> contacts) {
        this.contacts = contacts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
