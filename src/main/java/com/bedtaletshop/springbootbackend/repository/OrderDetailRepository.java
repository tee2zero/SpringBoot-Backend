package com.bedtaletshop.springbootbackend.repository;

import com.bedtaletshop.springbootbackend.model.OrderDetail;
import com.bedtaletshop.springbootbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findAllByUserId(Long userId);

    @Query(nativeQuery = true, value = "select o.* from order_detail o where o.user_id = :userId and o.status = :status")
    List<OrderDetail> findAllByUserIdAndStatus( @Param("userId") Long userId, @Param("status") String status);

    OrderDetail findBySlug(String url);

}
