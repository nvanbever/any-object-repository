package be.nvb.aor.domain.model.base;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.List;

public abstract class ProductDetails {

    private List<Asset> assets;

    public ProductDetails() {}

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public void addAsset(Asset asset) {
        assets.add(asset);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
