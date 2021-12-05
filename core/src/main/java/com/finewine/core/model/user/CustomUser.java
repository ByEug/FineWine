package com.finewine.core.model.user;

import com.finewine.core.model.inventory.Inventory;

import java.math.BigDecimal;

public class CustomUser {

    private Long id;
    private String clientName;
    private String clientSurname;
    private String username;
    private String password;
    private BigDecimal currentFundsBalance;
    private Boolean enabled;
    private Inventory inventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getCurrentFundsBalance() {
        return currentFundsBalance;
    }

    public void setCurrentFundsBalance(BigDecimal currentFundsBalance) {
        this.currentFundsBalance = currentFundsBalance;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
