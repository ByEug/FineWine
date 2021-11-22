package com.finewine.core.model.category;

public class Category {
    private Long id;
    private String categoryName;
    private String shortCategoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getShortCategoryName() {
        return shortCategoryName;
    }

    public void setShortCategoryName(String shortCategoryName) {
        this.shortCategoryName = shortCategoryName;
    }
}
