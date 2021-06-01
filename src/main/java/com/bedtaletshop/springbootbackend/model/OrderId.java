package com.bedtaletshop.springbootbackend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderId implements Serializable {


    private Long orderId;


    private Long userId;
}
