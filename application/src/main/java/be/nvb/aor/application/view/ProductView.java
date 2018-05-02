package be.nvb.aor.application.view;

public class ProductView<ANY_OBJECT extends ProductDetailsView> {

    private String id;
    private String name;
    private String description;
    private String catalogId;
    private PriceView price;
    private ANY_OBJECT details;
    private String productDetailsClassName;

    public ProductView() {
        super();
    }

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

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public PriceView getPrice() {
        return price;
    }

    public ANY_OBJECT getDetails() {
        return details;
    }

    public void setDetails(ANY_OBJECT details) {
        this.details = details;
    }

    public void setPrice(PriceView price) {
        this.price = price;
    }

    public void setProductDetailsClassName(String productDetailsClassName) {
        this.productDetailsClassName = productDetailsClassName;
    }

    public String getProductDetailsClassName() {
        return productDetailsClassName;
    }
}
