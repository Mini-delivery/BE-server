package me.woodgeon.minidelivery.repository;

import me.woodgeon.minidelivery.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM orders ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Order findLatestOrder();
}
