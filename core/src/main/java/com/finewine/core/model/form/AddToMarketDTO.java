package com.finewine.core.model.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddToMarketDTO {

    @NotNull
    @DecimalMin(value = "0.01")
    @Pattern(regexp = "\\d+\\.\\d+")
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
