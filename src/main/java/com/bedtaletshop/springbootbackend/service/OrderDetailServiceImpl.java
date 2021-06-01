package com.bedtaletshop.springbootbackend.service;

import com.bedtaletshop.springbootbackend.exception.NotFoundException;
import com.bedtaletshop.springbootbackend.model.OrderDetail;
import com.bedtaletshop.springbootbackend.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class OrderDetailServiceImpl implements OrderDetailService{

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getOrderDetailByUserId(Long userId) {
        List<OrderDetail> orderList = orderDetailRepository.findAllByUserId(userId);
        if(orderList.isEmpty()) throw new NotFoundException("Order not found with userId:" + userId);
        return orderList;
    }

    @Override
    public List<OrderDetail> getOrderDetailByUserIdAndStatus(Long userId, String status) {

        List<OrderDetail> orderList = orderDetailRepository.findAllByUserIdAndStatus(userId,status);
        if(orderList.isEmpty()) throw new NotFoundException("Not found your order");
        return orderDetailRepository.findAllByUserIdAndStatus(userId,status);
    }

    @Override
    public OrderDetail getOrderDetailBySlug(String url) {
        OrderDetail orderDetail = orderDetailRepository.findBySlug(url);
        if(orderDetail == null ) throw new NotFoundException("Not found your order");
        return orderDetail;
    }
}
