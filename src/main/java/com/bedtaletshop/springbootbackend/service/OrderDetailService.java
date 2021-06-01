package com.bedtaletshop.springbootbackend.service;

import com.bedtaletshop.springbootbackend.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetail> getOrderDetailByUserId(Long userId);
    List<OrderDetail> getOrderDetailByUserIdAndStatus(Long id,String status);
    OrderDetail getOrderDetailBySlug(String slug);
}
