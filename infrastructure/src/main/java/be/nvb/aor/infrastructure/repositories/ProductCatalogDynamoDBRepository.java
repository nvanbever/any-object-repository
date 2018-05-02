package be.nvb.aor.infrastructure.repositories;

import be.nvb.aor.domain.model.base.ProductCatalog;
import be.nvb.aor.domain.repositories.ProductCatalogRepository;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.xspec.ExpressionSpecBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.amazonaws.services.dynamodbv2.xspec.ExpressionSpecBuilder.S;

public class ProductCatalogDynamoDBRepository extends AbstractDynamoDBRepository<ProductCatalog<?>, String> implements ProductCatalogRepository {
    public static final String ID = "id";

    public ProductCatalogDynamoDBRepository() {
        super(ProductCatalogDynamoDBRepository::convert, ProductCatalogDynamoDBRepository::convert);
    }

    private static Item convert(ProductCatalog<?> productCatalog) {
        try {
            return new Item()
                    .withPrimaryKey(ID, productCatalog.getId())
                    .withString("detailsClassName", entityClassName(productCatalog))
                    .withJSON("productCatalog", new ObjectMapper().writeValueAsString(productCatalog));
        } catch (JsonProcessingException e) {
            throw new RepositoryException(e, productCatalog);
        }
    }

    private static String entityClassName(ProductCatalog<?> productCatalog) {
        Type type =
                ((ParameterizedType) productCatalog.getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
        return type.getTypeName();
    }

    private static ProductCatalog<?> convert(Item item) {
        try {
            String detailsClassName = item.getString("detailsClassName");
            ObjectMapper mapper = new ObjectMapper();
            JavaType type = mapper.getTypeFactory().constructParametricType(ProductCatalog.class, Class.forName(detailsClassName));
            return mapper.readValue(item.getJSON("productCatalog"), type);
        } catch (Exception e) {
            throw new RepositoryException(e, ProductCatalog.class, item.getString(ID));

        }
    }

    @Override
    public ProductCatalog findBySupplierIdAndName(String supplierId, String name) {
        ExpressionSpecBuilder builder = new ExpressionSpecBuilder();
        QuerySpec querySpec =
                new QuerySpec().withExpressionSpec(
                        builder
                                .withCondition(
                                        S("productCatalog.supplierId").eq(supplierId).and(
                                                S("productCatalog.name").eq(name)
                                        ))
                                .buildForQuery());

        List<ProductCatalog<?>> productCatalogs = fromItemCollection(table.query(querySpec));
        return productCatalogs.isEmpty() ? null : productCatalogs.get(0);
    }

    private List<ProductCatalog<?>> fromItemCollection(ItemCollection<QueryOutcome> result) {
        return
                StreamSupport
                        .stream(result.spliterator(), false)
                        .map(ProductCatalogDynamoDBRepository::convert)
                        .collect(Collectors.toList());
    }

    @Override
    String primaryKeyName() {
        return null;
    }
}
