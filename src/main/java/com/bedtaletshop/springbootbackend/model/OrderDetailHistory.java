package com.bedtaletshop.springbootbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Accessors(chain = true)
@Getter
@Setter
public class OrderDetailHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createDate;

    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDate;

//    private Long userId;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderDetail_id", referencedColumnName = "id")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    OrderDetail orderDetail;

}
