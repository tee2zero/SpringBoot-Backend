package com.bedtaletshop.springbootbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bedtaletshop.springbootbackend.model.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByName(String name);

	// SELECT * FROM Product WHERE name like '%foo%' AND stock > x order by stock
	// desc
	public List<Product> findByNameContainingAndStockGreaterThanOrderByStockDesc(String name, int stock);

	@Query(value = "SELECT + FROM PRODUCT WHERE STOCK = 0", nativeQuery = true)
	public List<Product> checkoutOfStock();

	@Query(nativeQuery = true, value = "select p.* from product p where p.status = :status and  parsedatetime(p.created_date,'yyyy-MM-dd')  = :createdDate")
	List<Product> selectAllByStatusAndDate(@Param("status") String status, @Param("createdDate") String createdDate);

	@Query("select p from product p where p.name like %:name")
	List<Product> selectAllByNameEndsWith(@Param("name") String name);

	@Modifying
	@Query("update product p set p.name = :name where p.price = :price")
	int updateNameByPrice(@Param("name") String name, @Param("price") int price);

	@Modifying
	@Query(nativeQuery = true, value = "delete from product p where p.id = :id")
	int removeAllByStatus(@Param("id") Long id);

}
