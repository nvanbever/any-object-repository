package be.nvb.aor.domain.repositories;

import be.nvb.aor.domain.model.base.ProductCatalog;

public interface ProductCatalogRepository extends AORRepository<ProductCatalog<?>, String> {

    ProductCatalog findBySupplierIdAndName(String supplierId, String name);
}
