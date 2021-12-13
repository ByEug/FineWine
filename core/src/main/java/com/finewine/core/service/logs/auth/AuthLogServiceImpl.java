package com.finewine.core.service.logs.auth;

import com.finewine.core.model.logs.auth.AuthLog;
import com.finewine.core.model.logs.auth.AuthLogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthLogServiceImpl implements AuthLogService {

    @Resource
    private AuthLogDao authLogDao;

    @Override
    public List<AuthLog> getForUserId(Long userId) {
        return authLogDao.getForUserId(userId);
    }

    @Override
    public void save(AuthLog authLog, Long userId) {
        authLogDao.save(authLog, userId);
    }
}
