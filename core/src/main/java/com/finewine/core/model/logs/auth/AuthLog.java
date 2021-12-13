package com.finewine.core.model.logs.auth;

import com.finewine.core.model.logs.AbstractLog;

public class AuthLog extends AbstractLog {

    private AuthLogAction action;

    public AuthLogAction getAction() {
        return action;
    }

    public void setAction(AuthLogAction action) {
        this.action = action;
    }
}
