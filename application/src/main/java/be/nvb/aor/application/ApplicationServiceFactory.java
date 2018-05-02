package be.nvb.aor.application;

import be.nvb.aor.application.services.ModelMapper;
import be.nvb.aor.application.services.ProductApplicationService;
import be.nvb.aor.application.services.ProductCatalogApplicationService;
import be.nvb.aor.application.services.SupplierApplicationService;

import java.util.Iterator;
import java.util.ServiceLoader;

public interface ApplicationServiceFactory {

    static ApplicationServiceFactory getInstance() {
        ServiceLoader<ApplicationServiceFactory> load = ServiceLoader.load(ApplicationServiceFactory.class);
        Iterator<ApplicationServiceFactory> iterator = load.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    ProductApplicationService productApplicationService();

    ProductCatalogApplicationService productCatalogApplicationService();

    SupplierApplicationService supplierApplicationService();

    ModelMapper modelMapper();
}
