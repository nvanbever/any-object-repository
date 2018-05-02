package be.nvb.aor.application.view;

import be.nvb.aor.domain.model.base.PriceUnit;

import java.math.BigDecimal;
import java.util.Currency;

public class PriceView {
    private BigDecimal amount;
    private Currency currency = Currency.getInstance("EUR");
    private PriceUnit unit;

    public PriceView() {
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
}
