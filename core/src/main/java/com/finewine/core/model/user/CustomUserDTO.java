package com.finewine.core.model.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class CustomUserDTO {

    @NotEmpty
    @Pattern(regexp = "^[\\p{L} .'-]+$")
    private String clientName;

    @NotEmpty
    @Pattern(regexp = "^[\\p{L} .'-]+$")
    private String clientSurname;

    @Email
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

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
}
