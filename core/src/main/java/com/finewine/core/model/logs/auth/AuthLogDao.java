package com.finewine.core.model.logs.auth;

import java.util.List;

public interface AuthLogDao {

    List<AuthLog> getForUserId(Long userId);

    void save(AuthLog authLog, Long userId);
}
