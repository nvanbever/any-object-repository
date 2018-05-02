package be.nvb.aor.infrastructure.repositories;

import be.nvb.aor.domain.model.Model;
import com.amazonaws.services.dynamodbv2.document.Item;

@FunctionalInterface
public interface ItemToEntityConverter<ENTITY extends Model> {

    ENTITY convert(Item item) throws Exception;
}
