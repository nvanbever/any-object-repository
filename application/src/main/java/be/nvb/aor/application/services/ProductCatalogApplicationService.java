package be.nvb.aor.application.services;

import be.nvb.aor.application.view.ProductCatalogView;
import be.nvb.aor.application.view.ProductView;

import java.io.IOException;
import java.util.List;

public interface ProductCatalogApplicationService {

    ProductCatalogView findProductCatalogById(String supplierId, String productCatalogId);

    List<ProductCatalogView> findProductCatalogByName(String supplierId, String name);

    List<ProductCatalogView> findProductCatalogs(String supplierId);

    ProductCatalogView createOrModifyProductCatalog(String supplierId, ProductCatalogView productCatalogView);

    void deleteProductCatalog(String supplierId, String productCatalogId);

    List<ProductView> findProducts(String supplierId, String productCatalogId);

    ProductView findProductById(String supplierId, String productCatalogId, String productId);

    ProductView createOrModifyProduct(String supplierId, String productCatalogId, ProductView productView);

}
