package be.nvb.aor.application.services;

import be.nvb.aor.application.view.ProductView;

import java.util.List;

public interface ProductApplicationService {
    List<ProductView> findProducts();

    ProductView findProductById(String productId);

    ProductView createOrModifyProduct(ProductView productView);
}
