package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
//    Product findByNameContaininga(String nameProduct);
    Product findAllByCategory_Id(int categoryID);

    @Query(value = "select u from User u where u.userName like %?1%")
    Page<Product> findByName(String name,Pageable pageable);

    Product findById(int id);

    public void deleteById(int id);

    @Query(value = "select u from User u")
    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM test  .products limit 0,5;",nativeQuery = true)
    public List<Product> findFirst5ByID();
}
