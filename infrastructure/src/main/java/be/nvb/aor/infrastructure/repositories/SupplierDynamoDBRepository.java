package be.nvb.aor.infrastructure.repositories;

import be.nvb.aor.domain.model.base.Supplier;
import be.nvb.aor.domain.repositories.SupplierRepository;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SupplierDynamoDBRepository extends AbstractDynamoDBRepository<Supplier, String> implements SupplierRepository {
    public static final String ID = "id";

    public SupplierDynamoDBRepository() {
        super(SupplierDynamoDBRepository::convert, SupplierDynamoDBRepository::convert);
    }

    private static Item convert(Supplier supplier) throws Exception {
        return new Item()
                .withPrimaryKey(ID, supplier.getId())
                .withJSON("supplier", new ObjectMapper().writeValueAsString(supplier));
    }

    private static Supplier convert(Item item) throws Exception {
        return new ObjectMapper().readValue(item.getJSON("supplier"), Supplier.class);
    }

    @Override
    String primaryKeyName() {
        return null;
    }
}
