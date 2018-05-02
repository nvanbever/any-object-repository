package be.nvb.aor.application.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = ProductCatalogViewDeserializer.class)
public class ProductCatalogView<ANY_OBJECT extends ProductCatalogDetailsView> {
    private String id;
    private String name;
    private String description;
    private String supplierId;
    private String productCatalogDetailsClassName;
    private ANY_OBJECT details;
    private List<ProductView> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public ANY_OBJECT getDetails() {
        return details;
    }

    public void setDetails(ANY_OBJECT details) {
        this.details = details;
    }

    public List<ProductView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductView> products) {
        this.products = products;
    }

    public String getProductCatalogDetailsClassName() {
        return productCatalogDetailsClassName;
    }

    public void setProductCatalogDetailsClassName(String productCatalogDetailsClassName) {
        this.productCatalogDetailsClassName = productCatalogDetailsClassName;
    }
}
