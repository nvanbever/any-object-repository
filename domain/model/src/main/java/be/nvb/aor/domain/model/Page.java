package be.nvb.aor.domain.model;

import java.util.List;

public class Page<MODEL extends Model<ID>, ID> {

    private Integer numberOfItemsPerPage;
    private final Integer totalNumberOfItems;
    private ID lastEvaluatedKey;
    private List<MODEL> models;

    public Page(Integer numberOfItemsPerPage, Integer totalNumberOfItems, ID lastEvaluatedKey) {
        this.numberOfItemsPerPage = numberOfItemsPerPage;
        this.totalNumberOfItems = totalNumberOfItems;
        this.lastEvaluatedKey = lastEvaluatedKey;
    }
}
