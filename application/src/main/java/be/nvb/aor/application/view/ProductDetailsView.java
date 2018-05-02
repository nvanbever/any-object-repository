package be.nvb.aor.application.view;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsView {

    private List<AssetView> assets = new ArrayList<>();

    public ProductDetailsView() {}

    public List<AssetView> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetView> assets) {
        this.assets = assets;
    }
}
