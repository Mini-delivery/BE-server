package me.woodgeon.minidelivery.repository;

import me.woodgeon.minidelivery.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
