package be.nvb.aor.application.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class ProductCatalogViewDeserializer extends JsonDeserializer<ProductCatalogView> {

    private Annotation[] annotations;

    @Override
    public ProductCatalogView deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);

        ProductCatalogView productCatalogView = new ProductCatalogView();

        productCatalogView.setId(nodeStringValue(node, "id"));
        productCatalogView.setName(nodeStringValue(node, "name"));
        productCatalogView.setDescription(nodeStringValue(node, "description"));
        productCatalogView.setProductCatalogDetailsClassName(nodeStringValue(node, "productCatalogDetailsClassName"));
        productCatalogView.setSupplierId(nodeStringValue(node, "supplierId"));
        productCatalogView.setSupplierId(nodeStringValue(node, "supplierId"));

        try {
            Class aClass = Class.forName(productCatalogView.getProductCatalogDetailsClassName());
            ProductCatalogDetailsView view = parseProductCatalogDetailsView(aClass, node.get("details"));
            productCatalogView.setDetails(view);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return productCatalogView;
    }

    private ProductCatalogDetailsView parseProductCatalogDetailsView(Class aClass, JsonNode jsonNode) throws IOException {
        ProductCatalogDetailsView view = null;
        if (jsonNode != null) {
            String details = jsonNode.toString();
            if (details != null && !details.equals("null") && details.length() > 0) {
                view = (ProductCatalogDetailsView) new ObjectMapper().readValue(details.toString(), aClass);
            }
        }
        return view;
    }

    private String nodeStringValue(JsonNode node, String id) {
        JsonNode jsonNode = node.get(id);
        return jsonNode != null ? jsonNode.textValue() : null;
    }
}
