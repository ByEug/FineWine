package com.finewine.core.service.logs.auth;

import com.finewine.core.model.logs.auth.AuthLog;

import java.util.List;

public interface AuthLogService {

    List<AuthLog> getForUserId(Long userId);

    void save(AuthLog authLog, Long userId);
}
