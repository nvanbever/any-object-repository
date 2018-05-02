package be.nvb.aor.application.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ProductViewDeserializer extends JsonDeserializer<ProductView> {

    @Override
    public ProductView deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);

        ProductView productView = new ProductView();

        productView.setId(nodeStringValue(node, "id"));
        productView.setName(nodeStringValue(node, "name"));
        productView.setDescription(nodeStringValue(node, "description"));
        productView.setProductDetailsClassName(nodeStringValue(node, "productDetailsClassName"));
        productView.setCatalogId(nodeStringValue(node, "catalogId"));

        try {
            Class aClass = Class.forName(productView.getProductDetailsClassName());
            ProductDetailsView view = parseProductDetailsView(aClass, node.get("details"));
            productView.setDetails(view);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return productView;
    }

    private ProductDetailsView parseProductDetailsView(Class aClass, JsonNode jsonNode) throws IOException {
        ProductDetailsView view = null;
        if (jsonNode != null) {
            String details = jsonNode.toString();
            if (details != null && !details.equals("null") && details.length() > 0) {
                view = (ProductDetailsView) new ObjectMapper().readValue(details.toString(), aClass);
            }
        }
        return view;
    }

    private String nodeStringValue(JsonNode node, String id) {
        JsonNode jsonNode = node.get(id);
        return jsonNode != null ? jsonNode.textValue() : null;
    }
}
