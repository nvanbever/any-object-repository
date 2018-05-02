package be.nvb.aor.infrastructure.repositories;

import be.nvb.aor.domain.model.Model;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDynamoDBRepository<ENTITY extends Model<TYPE>, TYPE> {

    private final DynamoDB dynamoDB;
    protected final Table table;
    private final Class<ENTITY> entityClass;
    private final Converter<ENTITY, Item> toItemConverter;
    private final Converter<Item, ENTITY> fromItemConverter;

    public AbstractDynamoDBRepository(Converter<ENTITY, Item> toItemConverter, Converter<Item, ENTITY> fromItemConverter) {
        this.toItemConverter = toItemConverter;
        this.fromItemConverter = fromItemConverter;
        this.entityClass = entityClass();
        this.dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.standard().build());
        this.table = dynamoDB.getTable(new ProfileBasedTableNameResolver().getTableName(entityClass));
    }

    private Class<ENTITY> entityClass() {
        return (Class<ENTITY>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public ENTITY save(ENTITY entity) {
        try {
            table.putItem(entityToItem(entity));

            return findOne(entity.getId());
        } catch (Exception e) {
            throw new RepositoryException(e, entity);
        }
    }

    public Collection<ENTITY> save(Collection<ENTITY> entities) {
        TableWriteItems entityWriteItems = new TableWriteItems(table.getTableName())
                .withItemsToPut(entities.stream().map(this::entityToItemThrowingRepositoryException).collect(Collectors.toList()));

        BatchWriteItemOutcome outcome = dynamoDB.batchWriteItem(entityWriteItems);

        // Check for unprocessed keys which could happen if you exceed
        // provisioned throughput
        while (outcome.getUnprocessedItems().size() > 0) {
            outcome = dynamoDB.batchWriteItemUnprocessed(outcome.getUnprocessedItems());
        }

        return findById((TYPE[]) entities.stream().map(Model::getId).toArray());
    }

    private Item entityToItemThrowingRepositoryException(ENTITY entity) {
        try {
            return entityToItem(entity);
        } catch (Exception e) {
            throw new RepositoryException(e, entity);
        }
    }

    public ENTITY findOne(TYPE id) {
        try {
            return itemToEntity(table.getItem(primaryKeyName(), id));
        } catch (Exception e) {
            throw new RepositoryException(e, entityClass, id);
        }
    }

    public Collection<ENTITY> findById(TYPE... ids) {
        TableKeysAndAttributes entityKeys =
                new TableKeysAndAttributes(table.getTableName()).addHashOnlyPrimaryKeys(primaryKeyName(), (Object[]) ids);

        BatchGetItemOutcome outcome = dynamoDB.batchGetItem(entityKeys);

        return getEntities(outcome);
    }

    private Collection<ENTITY> getEntities(BatchGetItemOutcome outcome) {
        List<ENTITY> result = new ArrayList<>();

        processItems(outcome, result);

        while (!outcome.getUnprocessedKeys().isEmpty()) {
            outcome = dynamoDB.batchGetItemUnprocessed(outcome.getUnprocessedKeys());
            processItems(outcome, result);
        }

        return result;
    }

    public void delete(ENTITY entity) {
        table.deleteItem(primaryKeyName(), entity.getId());
    }

    public Collection<ENTITY> findAll() {
        return getEntities(dynamoDB.batchGetItem());
    }

    private void processItems(BatchGetItemOutcome outcome, List<ENTITY> result) {
        outcome .getTableItems()
                .get(table.getTableName())
                .stream()
                .map(this::itemToEntityThrowingRepositoryException)
                .forEach(result::add);
    }

    private ENTITY itemToEntityThrowingRepositoryException(Item item) {
        try {
            return itemToEntity(item);
        } catch (Exception e) {
            throw new RepositoryException(e, entityClass, (TYPE) item.get(primaryKeyName()));
        }
    }

    private Item entityToItem(ENTITY entity) throws Exception {
        return toItemConverter.convert(entity);
    }

    private ENTITY itemToEntity(Item item) throws Exception {
        return fromItemConverter.convert(item);
    }

    abstract String primaryKeyName();
}
