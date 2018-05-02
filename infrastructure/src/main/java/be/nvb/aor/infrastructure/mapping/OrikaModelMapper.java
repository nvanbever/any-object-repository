package be.nvb.aor.infrastructure.mapping;

import be.nvb.aor.application.services.ModelMapper;
import be.nvb.aor.application.services.ModelMapperConfigurer;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrikaModelMapper implements ModelMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrikaModelMapper.class);
    private final MapperFacade facade;

    public OrikaModelMapper(ModelMapperConfigurer<MapperFactory>... configurers) {
        this.facade = this.configureNewMapperFacade(configurers);
    }

    private MapperFacade configureNewMapperFacade(ModelMapperConfigurer<MapperFactory>[] configurers) {
        MapperFactory factory = ((DefaultMapperFactory.Builder)(new DefaultMapperFactory.Builder()).useAutoMapping(false)).build();
        if (configurers != null && configurers.length > 0) {
            LOGGER.debug("Loading detected Orika configurers:");
            ModelMapperConfigurer<MapperFactory>[] var3 = configurers;
            int var4 = configurers.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                ModelMapperConfigurer<MapperFactory> configurer = var3[var5];
                configurer.configure(factory);
                LOGGER.debug("\t\t{}", configurer.getClass().getName());
            }
        } else {
            LOGGER.info("Attempted to build new MapperFacade but no configurers have been set");
        }

        return factory.getMapperFacade();
    }

    public <E, V> Iterable<V> map(Iterable<E> sources, Class<V> destination) {
        return this.facade.mapAsList(sources, destination);
    }

    @Override
    public <FROM, TO> TO map(FROM from, Class<TO> toClass) {
        return facade.map(from, toClass);
    }
}
