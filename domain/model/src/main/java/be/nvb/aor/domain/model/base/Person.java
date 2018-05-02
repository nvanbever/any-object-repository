package be.nvb.aor.domain.model.base;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Person {

    private String firstName;
    private String lastName;
    private List<ContactInformation> contacts = new ArrayList<>();
    private String id;

    public Person() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<ContactInformation> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactInformation> contacts) {
        this.contacts = contacts;
    }

    public Person identify() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
