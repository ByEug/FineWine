package com.finewine.core.model.product;

import com.finewine.core.model.category.Category;
import com.finewine.core.model.country.Country;
import com.finewine.core.model.shopstock.ShopStock;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

public class Product {
    private Long id;
    private String productName;
    private Date creatingDate;
    private Integer yearMade;
    private String picturePath;
    private BigDecimal price;
    private Country country;
    private ShopStock stock;
    private String description;
    private Set<Category> categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public Integer getYearMade() {
        return yearMade;
    }

    public void setYearMade(Integer yearMade) {
        this.yearMade = yearMade;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public ShopStock getStock() {
        return stock;
    }

    public void setStock(ShopStock stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
