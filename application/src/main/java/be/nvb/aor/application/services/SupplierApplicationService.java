package be.nvb.aor.application.services;

import be.nvb.aor.application.view.PersonView;
import be.nvb.aor.application.view.SupplierView;

import java.util.List;

public interface SupplierApplicationService {

    SupplierView createOrModifySupplier(SupplierView supplier);

    SupplierView addContact(String supplierId, PersonView contact);

    List<SupplierView> findAllSuppliers();
}
