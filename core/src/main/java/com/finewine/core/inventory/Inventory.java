package com.finewine.core.inventory;

public class Inventory {

    private Long id;
    private Integer overallQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOverallQuantity() {
        return overallQuantity;
    }

    public void setOverallQuantity(Integer overallQuantity) {
        this.overallQuantity = overallQuantity;
    }
}
