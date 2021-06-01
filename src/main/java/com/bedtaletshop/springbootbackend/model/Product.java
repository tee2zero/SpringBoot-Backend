package com.bedtaletshop.springbootbackend.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.experimental.Accessors;

// IF you use @Query in repository must be reference to entity name
@Entity(name = "product")
@Accessors(chain = true)
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false, unique = false)
	private String name;
	private String image;
	private int price;
	private int stock;

	@Setter(AccessLevel.NONE)
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createDate;

	@Setter(AccessLevel.NONE)
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updateDate;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "subCategory_id", referencedColumnName = "id")
	private SubCategory subCategory;

	@JsonIgnore
	@OneToMany(mappedBy = "product" )
	Set<OrderDetailHistory> orderDetailHistories;
}
