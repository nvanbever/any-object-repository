package be.nvb.aor.infrastructure.mapping;

import be.nvb.aor.application.mapping.AORMapperConfigurer;
import be.nvb.aor.application.view.ProductCatalogView;
import be.nvb.aor.application.view.ProductView;
import be.nvb.aor.application.view.SupplierView;
import be.nvb.aor.domain.model.base.*;
import be.nvb.aor.domain.model.castle.CastleCatalogDetails;
import be.nvb.aor.domain.model.castle.CastleDetails;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinition;
import io.github.benas.randombeans.FieldDefinitionBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import io.github.benas.randombeans.randomizers.collection.ListRandomizer;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

import static io.github.benas.randombeans.api.EnhancedRandom.random;

public class AORMapperConfigurerTest {
    private OrikaModelMapper modelMapper = new OrikaModelMapper(new AORMapperConfigurer());

    @Test
    public void testProductMapper() {
        EnhancedRandom enhancedRandom =
                EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                        .randomize(productDetailsFieldDefinition(), productDetailsRandomizer()).build();

        assertEquals(Product.class, ProductView.class, enhancedRandom);
    }

    @Test
    public void testProductCatalogMapper() {
        EnhancedRandom enhancedRandom =
                EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                        .randomize(productCatalogDetailsFieldDefinition(), productCatalogDetailsRandomizer()).build();

        assertEquals(ProductCatalog.class, ProductCatalogView.class, enhancedRandom);
    }

    @Test
    public void testSupplierMapper() {
        assertEquals(Supplier.class, SupplierView.class, EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build());
    }

    private <FROM, TO> void assertEquals(Class<FROM> fromClass, Class<TO> toClass, EnhancedRandom random) {
        FROM from = random.nextObject(fromClass);

        TO to = modelMapper.map(from, toClass);
        FROM fromAgain = modelMapper.map(to, fromClass);

        Assertions.assertThat(from).isEqualTo(fromAgain);
    }

    private Randomizer productDetailsRandomizer() {
        return new Randomizer() {
            @Override
            public Object getRandomValue() {
                EnhancedRandom enhancedRandom =
                        EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                                .randomize(
                                        productDetailsAssetsFieldDefinition(), new ListRandomizer<Asset>(new Randomizer<Asset>() {
                                            @Override
                                            public Asset getRandomValue() {
                                                return random(Asset.class);
                                            }
                                        })).build();

                return enhancedRandom.nextObject(CastleDetails.class);
            }
        };
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

                return enhancedRandom.nextObject(CastleCatalogDetails.class);
            }
        };
    }

    private FieldDefinition<?, ?> productDetailsAssetsFieldDefinition() {
        return FieldDefinitionBuilder
                .field()
                .ofType(List.class)
                .inClass(CastleDetails.class)
                .named("assets").get();
    }

    private FieldDefinition<?, ?> productCatalogDetailsAssetsFieldDefinition() {
        return FieldDefinitionBuilder
                .field()
                .ofType(List.class)
                .inClass(CastleCatalogDetails.class)
                .named("assets").get();
    }

    private FieldDefinition<?, ?> productDetailsFieldDefinition() {
        return FieldDefinitionBuilder
                .field()
                .named("details")
                .ofType(ProductDetails.class)
                .inClass(Product.class).get();
    }

    private FieldDefinition<?, ?> productCatalogDetailsFieldDefinition() {
        return FieldDefinitionBuilder
                .field()
                .named("details")
                .ofType(ProductCatalogDetails.class)
                .inClass(ProductCatalog.class).get();
    }

}