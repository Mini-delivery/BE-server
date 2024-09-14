package me.woodgeon.minidelivery.service;

import lombok.RequiredArgsConstructor;
import me.woodgeon.minidelivery.domain.Order;
import me.woodgeon.minidelivery.dto.order.AddOrderRequest;
import me.woodgeon.minidelivery.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public Order save(AddOrderRequest request) {
        return orderRepository.save(request.toEntity());
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
