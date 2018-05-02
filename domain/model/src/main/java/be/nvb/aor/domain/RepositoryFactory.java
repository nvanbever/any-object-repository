package be.nvb.aor.domain;

import be.nvb.aor.domain.repositories.ProductCatalogRepository;
import be.nvb.aor.domain.repositories.ProductRepository;
import be.nvb.aor.domain.repositories.SupplierRepository;

import java.util.Iterator;
import java.util.ServiceLoader;

public interface RepositoryFactory {

    static RepositoryFactory getInstance() {
        ServiceLoader<RepositoryFactory> load = ServiceLoader.load(RepositoryFactory.class);
        Iterator<RepositoryFactory> iterator = load.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    ProductRepository productRepository();

    ProductCatalogRepository productCatalogRepository();

    SupplierRepository supplierRepository();
}
