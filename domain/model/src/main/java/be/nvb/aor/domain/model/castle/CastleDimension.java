package be.nvb.aor.domain.model.castle;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.math.BigDecimal;

public class CastleDimension {

    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal height;

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
