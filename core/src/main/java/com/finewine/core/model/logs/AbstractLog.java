package com.finewine.core.model.logs;

import java.sql.Date;

public abstract class AbstractLog {

    private Long id;
    private Date creatingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }
}
