package com.finewine.core.service.logs.order;

import com.finewine.core.model.logs.order.OrderCreatingLog;

import java.util.List;

public interface OrderCreatingLogService {

    List<OrderCreatingLog> getForUserId(Long userId);

    void save(OrderCreatingLog orderCreatingLog, Long userId);
}
