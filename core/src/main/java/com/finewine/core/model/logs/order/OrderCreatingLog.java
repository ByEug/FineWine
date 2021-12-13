package com.finewine.core.model.logs.order;

import com.finewine.core.model.logs.AbstractLog;
import com.finewine.core.model.order.Order;
import com.finewine.core.model.order.OrderType;

public class OrderCreatingLog extends AbstractLog {

    private OrderType orderType;
    private Order order;

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
