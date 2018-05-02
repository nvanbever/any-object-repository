package be.nvb.aor.infrastructure;

import be.nvb.aor.application.ApplicationServiceFactory;
import be.nvb.aor.application.services.*;
import be.nvb.aor.domain.RepositoryFactory;
import be.nvb.aor.infrastructure.mapping.OrikaModelMapper;

public class DefaultApplicationServiceFactory implements ApplicationServiceFactory {

    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();

    @Override
    public ProductApplicationService productApplicationService() {
        return new DefaultProductApplicationService(repositoryFactory.productRepository(), modelMapper());
    }

    @Override
    public ProductCatalogApplicationService productCatalogApplicationService() {
        return new DefaultProductCatalogApplicationService(repositoryFactory.productCatalogRepository(), repositoryFactory.productRepository(), modelMapper());
    }

    @Override
    public SupplierApplicationService supplierApplicationService() {
        return new DefaultSupplierApplicationService(repositoryFactory.supplierRepository(), modelMapper());
    }

    @Override
    public ModelMapper modelMapper() {
        return new OrikaModelMapper();
    }
}
