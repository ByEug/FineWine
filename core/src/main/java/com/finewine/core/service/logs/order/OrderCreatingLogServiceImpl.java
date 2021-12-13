package com.finewine.core.service.logs.order;

import com.finewine.core.model.logs.order.OrderCreatingLog;
import com.finewine.core.model.logs.order.OrderCreatingLogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderCreatingLogServiceImpl implements OrderCreatingLogService {

    @Resource
    private OrderCreatingLogDao orderCreatingLogDao;

    @Override
    public List<OrderCreatingLog> getForUserId(Long userId) {
        return orderCreatingLogDao.getForUserId(userId);
    }

    @Override
    public void save(OrderCreatingLog orderCreatingLog, Long userId) {
        orderCreatingLogDao.save(orderCreatingLog, userId);
    }
}
