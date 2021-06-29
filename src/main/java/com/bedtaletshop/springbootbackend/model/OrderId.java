package com.bedtaletshop.springbootbackend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
// This way need to cannot gen auto id need to do yourself
public class OrderId implements Serializable {


    private Long orderId;


    private Long userId;
}
