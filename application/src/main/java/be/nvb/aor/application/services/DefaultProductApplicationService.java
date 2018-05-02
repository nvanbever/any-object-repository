package be.nvb.aor.application.services;

import be.nvb.aor.application.view.ProductView;
import be.nvb.aor.domain.model.base.Product;
import be.nvb.aor.domain.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultProductApplicationService implements ProductApplicationService {

    private Logger log = LoggerFactory.getLogger(DefaultProductApplicationService.class);

    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    public DefaultProductApplicationService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductView> findProducts() {
        return productRepository.findAll().stream().map(product -> modelMapper.map(product, ProductView.class)).collect(Collectors.toList());
    }

    @Override
    public ProductView findProductById(String productId) {
        Product product = productRepository.findById(productId);

        return modelMapper.map(product, ProductView.class);
    }

    @Override
    public ProductView createOrModifyProduct(ProductView productView) {
        Product product =
                productRepository.save(new Product(productView.getName()));

        return modelMapper.map(product, ProductView.class);
    }
}
