package be.nvb.aor.domain.model.castle;

import be.nvb.aor.domain.model.base.ProductDetails;

public class CastleDetails extends ProductDetails {
    private CastleDimension size;

    public CastleDimension getSize() {
        return size;
    }

    public void setSize(CastleDimension size) {
        this.size = size;
    }
}
