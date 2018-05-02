package be.nvb.aor.application.view;

import be.nvb.aor.domain.model.base.AssetType;

public class AssetView {

    private String name;
    private String caption;
    private AssetType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }
}
