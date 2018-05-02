package be.nvb.aor.domain.model.base;

import be.nvb.aor.domain.model.Model;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.List;

public class Supplier implements Model<String> {

    private String id;
    private String name;
    private String productDetailsClass;
    private String productCatalogDetailsClass;
    private List<Person> contacts;

    public Supplier() {}

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getProductDetailsClass() {
        return productDetailsClass;
    }

    public void setProductDetailsClass(String productDetailsClass) {
        this.productDetailsClass = productDetailsClass;
    }

    public String getProductCatalogDetailsClass() {
        return productCatalogDetailsClass;
    }

    public void setProductCatalogDetailsClass(String productCatalogDetailsClass) {
        this.productCatalogDetailsClass = productCatalogDetailsClass;
    }

    public void addContact(Person person) {
        contacts.add(person.identify());
    }

    public void setContacts(List<Person> contacts) {
        this.contacts = contacts;
    }

    public List<Person> getContacts() {
        return contacts;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
