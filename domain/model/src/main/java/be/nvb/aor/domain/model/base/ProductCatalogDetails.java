package be.nvb.aor.domain.model.base;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.List;

public abstract class ProductCatalogDetails {

    private List<Asset> assets;

    public ProductCatalogDetails() {}

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
