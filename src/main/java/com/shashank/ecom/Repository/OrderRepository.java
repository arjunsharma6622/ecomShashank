package com.shashank.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shashank.ecom.models.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
