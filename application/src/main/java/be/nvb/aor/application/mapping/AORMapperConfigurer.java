package be.nvb.aor.application.mapping;

import be.nvb.aor.application.services.ModelMapperConfigurer;
import be.nvb.aor.application.view.*;
import be.nvb.aor.domain.model.base.*;
import be.nvb.aor.domain.model.castle.CastleCatalogDetails;
import be.nvb.aor.domain.model.castle.CastleDetails;
import be.nvb.aor.domain.model.castle.CastleDimension;
import ma.glasnost.orika.MapperFactory;

public class AORMapperConfigurer implements ModelMapperConfigurer<MapperFactory> {
    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(Person.class, PersonView.class).byDefault().register();
        mapperFactory.classMap(AddressData.class, AddressDataView.class).byDefault().register();
        mapperFactory.classMap(PhoneData.class, PhoneDataView.class).byDefault().register();
        mapperFactory.classMap(EmailAddressData.class, EmailAddressDataView.class).byDefault().register();
        mapperFactory.classMap(Asset.class, AssetView.class).byDefault().register();
        mapperFactory.classMap(ProductCatalog.class, ProductCatalogView.class).byDefault().register();
        mapperFactory.classMap(CastleDetails.class, CastleDetailsView.class).byDefault().register();
        mapperFactory.classMap(CastleCatalogDetails.class, CastleCatalogDetailsView.class).byDefault().register();
        mapperFactory.classMap(CastleDimension.class, CastleDimensionView.class).byDefault().register();
        mapperFactory.classMap(Product.class, ProductView.class).byDefault().register();
        mapperFactory.classMap(Price.class, PriceView.class).byDefault().register();
        mapperFactory.classMap(Supplier.class, SupplierView.class).byDefault().register();
    }
}
