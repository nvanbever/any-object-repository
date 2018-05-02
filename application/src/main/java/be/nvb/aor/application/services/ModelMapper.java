package be.nvb.aor.application.services;

public interface ModelMapper {

    <FROM, TO> TO map(FROM from, Class<TO> toClass);
}
