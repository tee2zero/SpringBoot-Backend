package com.bedtaletshop.springbootbackend.controller.api;

import com.bedtaletshop.springbootbackend.controller.request.OrderRequest;
import com.bedtaletshop.springbootbackend.controller.request.ProductRequest;
import com.bedtaletshop.springbootbackend.exception.NotFoundException;
import com.bedtaletshop.springbootbackend.model.OrderDetail;
import com.bedtaletshop.springbootbackend.model.Product;
import com.bedtaletshop.springbootbackend.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.criterion.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor

@Log4j2
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @GetMapping("/public/get/{userId}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(orderDetailService.getOrderDetailByUserId(userId));
    }

    @GetMapping("/public/get/{userId}/status/{status}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailByUserIdAndStatus(@PathVariable long userId,@PathVariable String status) {
        log.debug("Param userId:{}  status:{}",userId,status);
        return ResponseEntity.ok(orderDetailService.getOrderDetailByUserIdAndStatus(userId,status));
    }

    @GetMapping("/public/order/{slug}")
    public ResponseEntity<OrderDetail> getOrderDetailBySlug(@PathVariable String slug){
        return ResponseEntity.ok(orderDetailService.getOrderDetailBySlug(slug));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public ResponseEntity<Product> addOder(@Valid OrderRequest orderReq, BindingResult bindingResult) {


        return null;
    }
}

