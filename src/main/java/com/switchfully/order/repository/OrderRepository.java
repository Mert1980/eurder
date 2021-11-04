package com.switchfully.order.repository;

import com.switchfully.order.model.entity.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRepository {

    final HashMap<String, Order> orders;

    public OrderRepository() {
        this.orders = new HashMap<>();
    }

    public Order createOrder(Order order){
        Order newOrder = orders.put(order.getId(), order);
        return newOrder;
    }

    public List<Order> getAllOrders(){
        return new ArrayList<>(orders.values());
    }
}
