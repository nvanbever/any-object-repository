package be.nvb.aor.application.view;

import be.nvb.aor.domain.model.base.ContactInformationType;

public class ContactInformationView<CONTACT extends ContactDataView> {
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
}
