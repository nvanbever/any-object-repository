package be.nvb.aor.application.services;

import be.nvb.aor.application.view.PersonView;
import be.nvb.aor.application.view.SupplierView;
import be.nvb.aor.domain.model.base.Person;
import be.nvb.aor.domain.model.base.Supplier;
import be.nvb.aor.domain.repositories.SupplierRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultSupplierApplicationService implements SupplierApplicationService {

    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;

    public DefaultSupplierApplicationService(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SupplierView createOrModifySupplier(SupplierView supplier) {
        return modelMapper.map(supplierRepository.save(modelMapper.map(supplier, Supplier.class)), SupplierView.class);
    }

    @Override
    public SupplierView addContact(String supplierId, PersonView contact) {
        Supplier supplier = supplierRepository.findOne(supplierId);

        supplier.addContact(modelMapper.map(contact, Person.class));

        return modelMapper.map(supplier, SupplierView.class);
    }

    @Override
    public List<SupplierView> findAllSuppliers() {
        return supplierRepository.findAll().stream().map(supplier -> modelMapper.map(supplier, SupplierView.class)).collect(Collectors.toList());
    }
}
