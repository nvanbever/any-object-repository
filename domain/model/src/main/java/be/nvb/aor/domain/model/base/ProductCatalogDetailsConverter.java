package be.nvb.aor.domain.model.base;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductCatalogDetailsConverter implements DynamoDBTypeConverter<Map, ProductCatalogDetails> {

    private static final String USED_TYPE_PARAMETER = "usedType";

    private DynamoDBTypeConverter<Map, Asset> converter =
            DynamoDBTypeConverterFactory.standard().getConverter(Map.class, Asset.class);

    @Override
    public Map convert(ProductCatalogDetails object) {
        HashMap map = new HashMap();
        Class<? extends ProductCatalogDetails> aClass = object.getClass();
        map.put(USED_TYPE_PARAMETER, aClass);

        List<Map> assets = object.getAssets().stream().map(converter::convert).collect(Collectors.toList());

        map.put("assets", assets);

        return map;
    }

    @Override
    public ProductCatalogDetails unconvert(Map object) {
        try {
            Class<? extends ProductCatalogDetails> usedType = (Class<? extends ProductCatalogDetails>) Class.forName((String) object.get(USED_TYPE_PARAMETER));
            ProductCatalogDetails productCatalogDetails = usedType.newInstance();

            List<Map> assets = (List<Map>) object.get("assets");
            productCatalogDetails.setAssets(
                    assets.stream().map(converter::unconvert).collect(Collectors.toList())
            );

            return productCatalogDetails;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(String.format("Cannot unconvert map %s, %S", object, e));
        }
    }
}
