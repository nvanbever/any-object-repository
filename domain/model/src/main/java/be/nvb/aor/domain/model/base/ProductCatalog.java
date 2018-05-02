package be.nvb.aor.domain.model.base;

import be.nvb.aor.domain.model.Model;
import org.apache.commons.lang.builder.EqualsBuilder;

public class ProductCatalog<ANY_OBJECT extends ProductCatalogDetails> implements Model<String> {
    private String id;
    private String name;
    private String description;
    private String supplierId;
    private String productCatalogDetailsClassName;
    private ANY_OBJECT details;

    public ProductCatalog() {}

    public ProductCatalog(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
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

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public String getProductCatalogDetailsClassName() {
        return productCatalogDetailsClassName;
    }

    public void setProductCatalogDetailsClassName(String productCatalogDetailsClassName) {
        this.productCatalogDetailsClassName = productCatalogDetailsClassName;
    }
}
