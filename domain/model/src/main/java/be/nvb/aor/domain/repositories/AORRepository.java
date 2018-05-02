package be.nvb.aor.domain.repositories;

import be.nvb.aor.domain.model.Model;

import java.util.Collection;

public interface AORRepository<MODEL extends Model<ID>, ID> {

    MODEL findOne(ID id);

    Collection<MODEL> findById(ID... ids);

    MODEL save(MODEL entity);

    Collection<MODEL> save(Collection<MODEL> entities);

    void delete(MODEL entity);

    Collection<MODEL> findAll();
}
