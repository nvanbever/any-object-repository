package be.nvb.aor.application.view;

import java.util.List;

public class SupplierView {
    private String id;
    private String name;
    private String productDetailsClass;
    private String productCatalogDetailsClass;
    private List<PersonView> contacts;

    public String getId() {
        return id;
    }

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

    public List<PersonView> getContacts() {
        return contacts;
    }

    public void setContacts(List<PersonView> contacts) {
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
