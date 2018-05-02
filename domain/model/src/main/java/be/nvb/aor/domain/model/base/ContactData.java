package be.nvb.aor.domain.model.base;

import org.apache.commons.lang.builder.EqualsBuilder;

public abstract class ContactData {

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
