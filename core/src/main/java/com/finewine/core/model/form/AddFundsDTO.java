package com.finewine.core.model.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddFundsDTO {

    @NotNull
    @DecimalMin(value = "0.01")
    @Pattern(regexp = "\\d+\\.\\d+")
    private String funds;

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }
}
