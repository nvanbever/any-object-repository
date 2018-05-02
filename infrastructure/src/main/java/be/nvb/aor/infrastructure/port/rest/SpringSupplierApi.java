package be.nvb.aor.infrastructure.port.rest;

import be.nvb.aor.application.ApplicationServiceFactory;
import be.nvb.aor.application.view.PersonView;
import be.nvb.aor.application.view.ProductCatalogView;
import be.nvb.aor.application.view.SupplierView;

import java.io.IOException;
import java.util.List;

public class SpringSupplierApi {

    private static ApplicationServiceFactory factory = ApplicationServiceFactory.getInstance();

    public static ProductCatalogView findProductCatalogById(String supplierId, String productCatalogId) {
        return factory.productCatalogApplicationService().findProductCatalogById(supplierId, productCatalogId);
    }

    public static List<ProductCatalogView> findProductCatalogs(String supplierId, String name) {
        return factory.productCatalogApplicationService().findProductCatalogByName(supplierId, name);
    }

    public static ProductCatalogView createProductCatalog(String supplierId, ProductCatalogView productCatalogView) throws IOException {
        return factory.productCatalogApplicationService().createOrModifyProductCatalog(supplierId, productCatalogView);
    }

    public static ProductCatalogView modifyProductCatalog(String supplierId, String productCatalogId, ProductCatalogView productCatalogView) {
        return factory.productCatalogApplicationService().createOrModifyProductCatalog(supplierId, productCatalogView);
    }

    public static String deleteProductCatalog(String supplierId,String productCatalogId) {
        factory.productCatalogApplicationService().deleteProductCatalog(supplierId, productCatalogId);
        return productCatalogId;
    }

    public static List<SupplierView> findAllSuppliers() {
        return factory.supplierApplicationService().findAllSuppliers();
    }

    public static SupplierView createSupplier(SupplierView supplierView) {
        return factory.supplierApplicationService().createOrModifySupplier(supplierView);
    }

    public static SupplierView modifySupplier(String supplierId, SupplierView supplierView) {
        return factory.supplierApplicationService().createOrModifySupplier(supplierView);
    }

    public static SupplierView addContact(String supplierId, PersonView personView) {
        return factory.supplierApplicationService().addContact(supplierId, personView);
    }
}
