package be.nvb.aor.infrastructure.repositories;

@FunctionalInterface
public interface Converter<FROM, TO> {

    TO convert(FROM entity) throws Exception;
}
