package be.nvb.aor.domain.model.base;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.math.BigDecimal;
import java.util.Currency;

public class Price {
    private BigDecimal amount;
    private Currency currency = Currency.getInstance("EUR");
    private PriceUnit unit;

    public Price() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public PriceUnit getUnit() {
        return unit;
    }

    public void setUnit(PriceUnit unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
