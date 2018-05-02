package be.nvb.aor.infrastructure.repositories;

import be.nvb.aor.domain.model.base.Product;
import be.nvb.aor.domain.repositories.ProductRepository;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ProductDynamoDBRepository extends AbstractDynamoDBRepository<Product<?>, String> implements ProductRepository {

    public static final String ID = "id";

    public ProductDynamoDBRepository() {
        super(ProductDynamoDBRepository::convert, ProductDynamoDBRepository::convert);
    }

    private static Item convert(Product<?> product) throws Exception {
        return new Item()
                .withPrimaryKey(ID, product.getId())
                .withString("detailsClassName", entityClassName(product))
                .withJSON("product", new ObjectMapper().writeValueAsString(product));
    }

    private static String entityClassName(Product<?> product) {
        Type type =
                ((ParameterizedType) product.getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
        return type.getTypeName();
    }

    private static Product<?> convert(Item item) throws Exception {
        String detailsClassName = item.getString("detailsClassName");
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructParametricType(Product.class, Class.forName(detailsClassName));
        return mapper.readValue(item.getJSON("product"), type);
    }

    @Override
    String primaryKeyName() {
        return ID;
    }
}
