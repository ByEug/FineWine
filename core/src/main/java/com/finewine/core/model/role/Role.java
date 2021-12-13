package com.finewine.core.model.role;

public class Role {

    private Long id;
    private EnumRole roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumRole getRoleName() {
        return roleName;
    }

    public void setRoleName(EnumRole roleName) {
        this.roleName = roleName;
    }
}
