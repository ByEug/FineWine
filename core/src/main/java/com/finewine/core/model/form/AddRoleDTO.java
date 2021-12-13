package com.finewine.core.model.form;

import javax.validation.constraints.NotNull;

public class AddRoleDTO {

    @NotNull
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
