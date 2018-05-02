package be.nvb.aor.application.services;

import be.nvb.aor.application.view.ProductCatalogView;
import be.nvb.aor.application.view.ProductView;
import be.nvb.aor.domain.model.base.Product;
import be.nvb.aor.domain.model.base.ProductCatalog;
import be.nvb.aor.domain.repositories.ProductCatalogRepository;
import be.nvb.aor.domain.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultProductCatalogApplicationService implements ProductCatalogApplicationService {

    private Logger log = LoggerFactory.getLogger(DefaultProductApplicationService.class);

    private ProductCatalogRepository productCatalogRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    public DefaultProductCatalogApplicationService(ProductCatalogRepository productCatalogRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.productCatalogRepository = productCatalogRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductCatalogView> findProductCatalogByName(String supplierId, String name) {
        if (name == null) {
            return findProductCatalogs(supplierId);
        }

        ProductCatalog productCatalog = productCatalogRepository.findBySupplierIdAndName(supplierId, name);
        return Arrays.asList(modelMapper.map(productCatalog, ProductCatalogView.class));
    }

    @Override
    public ProductCatalogView findProductCatalogById(String supplierId, String productCatalogId) {
        ProductCatalog productCatalog = productCatalogRepository.findOne(productCatalogId);
        ProductCatalogView productCatalogView = modelMapper.map(productCatalog, ProductCatalogView.class);
        return addProducts(supplierId, productCatalogView);
    }

    private ProductCatalogView addProducts(String supplierId, ProductCatalogView productCatalogView) {

        productCatalogView.setProducts(
                findProducts(supplierId, productCatalogView.getId()));
        return productCatalogView;
    }

    @Override
    public List<ProductCatalogView> findProductCatalogs(String supplierId) {
        return productCatalogRepository
                .findAll()
                .stream()
                .map(productCatalog -> addProducts(supplierId, modelMapper.map(productCatalog, ProductCatalogView.class)))
                .collect(Collectors.toList());
    }

    @Override
    public ProductCatalogView createOrModifyProductCatalog(String supplierId, ProductCatalogView productCatalogView) {
        return addProducts(supplierId, modelMapper.map(productCatalogRepository.save(modelMapper.map(productCatalogView, ProductCatalog.class)), ProductCatalogView.class));
    }

    @Override
    public void deleteProductCatalog(String supplierId, String productCatalogId) {
        productCatalogRepository.delete(productCatalogRepository.findOne(productCatalogId));
    }

    @Override
    public List<ProductView> findProducts(String supplierId, String productCatalogId) {
        return productRepository.findAll().stream().map(product -> modelMapper.map(product, ProductView.class)).collect(Collectors.toList());
    }

    @Override
    public ProductView findProductById(String supplierId, String productCatalogId, String productId) {
        Product product = productRepository.findOne(productId);

        return modelMapper.map(product, ProductView.class);
    }

    @Override
    public ProductView createOrModifyProduct(String supplierId, String productCatalogId, ProductView productView) {
        Product product =
                productRepository.save(new Product(productView.getName()));

        return modelMapper.map(product, ProductView.class);
    }
}
