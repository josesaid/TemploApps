package com.cursomicroservicios.ioc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursomicroservicios.ioc.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
