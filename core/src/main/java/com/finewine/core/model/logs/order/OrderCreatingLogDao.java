package com.finewine.core.model.logs.order;

import java.util.List;

public interface OrderCreatingLogDao {

    List<OrderCreatingLog> getForUserId(Long userId);

    void save(OrderCreatingLog orderCreatingLog, Long userId);
}
