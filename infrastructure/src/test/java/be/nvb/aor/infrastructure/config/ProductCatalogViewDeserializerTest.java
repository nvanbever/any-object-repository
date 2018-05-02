package be.nvb.aor.infrastructure.config;

import be.nvb.aor.application.view.CastleCatalogDetailsView;
import be.nvb.aor.application.view.ProductCatalogDetailsView;
import be.nvb.aor.application.view.ProductCatalogView;
import be.nvb.aor.application.view.ProductCatalogViewDeserializer;
import be.nvb.aor.domain.model.base.Asset;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinition;
import io.github.benas.randombeans.FieldDefinitionBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import io.github.benas.randombeans.randomizers.collection.ListRandomizer;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static io.github.benas.randombeans.api.EnhancedRandom.random;

public class ProductCatalogViewDeserializerTest {

    @Test
    public void testProductCatalogViewDeserializer() throws IOException {
        EnhancedRandom enhancedRandom =
                EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                        .randomize(productCatalogDetailsFieldDefinition(), productCatalogDetailsRandomizer()).build();

        ProductCatalogView productCatalogView = enhancedRandom.nextObject(ProductCatalogView.class);

        productCatalogView.setProductCatalogDetailsClassName(CastleCatalogDetailsView.class.getName());

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(ProductCatalogView.class, new ProductCatalogViewDeserializer());
        mapper.registerModule(module);

        String content = mapper.writeValueAsString(productCatalogView);
        ProductCatalogView readValue = mapper.readValue(content, ProductCatalogView.class);
    }

    private FieldDefinition<?, ?> productCatalogDetailsFieldDefinition() {
        return FieldDefinitionBuilder
                .field()
                .named("details")
                .ofType(ProductCatalogDetailsView.class)
                .inClass(ProductCatalogView.class).get();
    }

    private FieldDefinition<?, ?> productCatalogDetailsAssetsFieldDefinition() {
        return FieldDefinitionBuilder
                .field()
                .ofType(List.class)
                .inClass(CastleCatalogDetailsView.class)
                .named("assets").get();
    }

    private Randomizer productCatalogDetailsRandomizer() {
        return new Randomizer() {
            @Override
            public Object getRandomValue() {
                EnhancedRandom enhancedRandom =
                        EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                                .randomize(
                                        productCatalogDetailsAssetsFieldDefinition(), new ListRandomizer<Asset>(new Randomizer<Asset>() {
                                            @Override
                                            public Asset getRandomValue() {
                                                return random(Asset.class);
                                            }
                                        })).build();

                return enhancedRandom.nextObject(CastleCatalogDetailsView.class);
            }
        };
    }
}