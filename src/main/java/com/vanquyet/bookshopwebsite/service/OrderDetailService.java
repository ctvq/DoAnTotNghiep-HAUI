package com.vanquyet.bookshopwebsite.service;

import com.vanquyet.bookshopwebsite.entity.Order;
import com.vanquyet.bookshopwebsite.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetailByOrder(Order order);
}
