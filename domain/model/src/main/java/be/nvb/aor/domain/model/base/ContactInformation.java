package be.nvb.aor.domain.model.base;

import org.apache.commons.lang.builder.EqualsBuilder;

public class ContactInformation<CONTACT extends ContactData> {
    private ContactInformationType type;
    private CONTACT contact;

    public ContactInformationType getType() {
        return type;
    }

    public void setType(ContactInformationType type) {
        this.type = type;
    }

    public CONTACT getContact() {
        return contact;
    }

    public void setContact(CONTACT contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
