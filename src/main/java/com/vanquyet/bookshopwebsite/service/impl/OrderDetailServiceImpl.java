package com.vanquyet.bookshopwebsite.service.impl;

import com.vanquyet.bookshopwebsite.entity.Order;
import com.vanquyet.bookshopwebsite.entity.OrderDetail;
import com.vanquyet.bookshopwebsite.repository.OrderDetailRepository;
import com.vanquyet.bookshopwebsite.service.OrderDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAllOrderDetailByOrder(Order order) {
        return orderDetailRepository.findByOrder(order);
    }
}
