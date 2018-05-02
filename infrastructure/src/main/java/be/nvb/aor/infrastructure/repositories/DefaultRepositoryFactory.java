package be.nvb.aor.infrastructure.repositories;

import be.nvb.aor.domain.RepositoryFactory;
import be.nvb.aor.domain.repositories.ProductCatalogRepository;
import be.nvb.aor.domain.repositories.ProductRepository;
import be.nvb.aor.domain.repositories.SupplierRepository;

public class DefaultRepositoryFactory implements RepositoryFactory {
    @Override
    public ProductRepository productRepository() {
        return new ProductDynamoDBRepository();
    }

    @Override
    public ProductCatalogRepository productCatalogRepository() {
        return new ProductCatalogDynamoDBRepository();
    }

    @Override
    public SupplierRepository supplierRepository() {
        return new SupplierDynamoDBRepository();
    }
}
