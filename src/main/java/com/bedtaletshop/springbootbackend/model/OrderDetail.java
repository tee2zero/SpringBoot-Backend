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
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(indexes = {
        @Index(columnList = "status", name = "status_index")
})
@IdClass(OrderId.class)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderId;

    @Id
    @Column(name = "user_id")
    private Long userId;

    private String slug;
    private String telephone;
    private String email;

    @OneToMany(mappedBy = "orderDetail")
    Set<OrderDetailHistory> products;

    @Enumerated(EnumType.STRING)
    Status status;

    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    private LocalDateTime createDate;

    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    public enum Status{
        WAITING,
        CANCEL,
        COMPLETE
    }

    //    @ManyToMany
//    @JoinTable(
//            name = "orders",
//            joinColumns = @JoinColumn(name = "orderdetail_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    List<Product> orders ;
}
