package be.nvb.aor.domain.model.base;

import be.nvb.aor.domain.model.Model;
import org.apache.commons.lang.builder.EqualsBuilder;

public class Product<ANY_OBJECT extends ProductDetails> implements Model<String> {
    private String id;
    private String name;
    private String description;
    private String catalogId;
    private Price price;
    private ANY_OBJECT details;

    public Product() {}

    public Product(String name) {
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

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public ANY_OBJECT getDetails() {
        return details;
    }

    public void setDetails(ANY_OBJECT details) {
        this.details = details;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
