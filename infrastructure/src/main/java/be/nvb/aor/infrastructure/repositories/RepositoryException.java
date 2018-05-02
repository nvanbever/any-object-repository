package be.nvb.aor.infrastructure.repositories;

import be.nvb.aor.domain.model.Model;
import com.amazonaws.services.dynamodbv2.document.Item;

@SuppressWarnings("serial")
public class RepositoryException extends RuntimeException {
    private final String entityName;
    private final Object id;

    public <ENTITY extends Model> RepositoryException(Throwable cause, Class<ENTITY> entityName, Item item) {
        this(cause, entityName, item.get("id"));
    }

    public <ENTITY extends Model> RepositoryException(Throwable cause, ENTITY event) {
        this(cause, event.getClass(), event.getId());
    }

    public <ENTITY extends Model<TYPE>, TYPE> RepositoryException(Throwable cause, Class<ENTITY> entityName, TYPE id) {
        super("Error for " + entityName + " " + id, cause);
        this.entityName = entityName.getSimpleName();
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public Object getId() {
        return id;
    }
}
