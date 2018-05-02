package be.nvb.aor.application.view;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalogDetailsView {

    private List<AssetView> assets;

    public ProductCatalogDetailsView() {}

    public List<AssetView> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetView> assets) {
        this.assets = assets;
    }
}
