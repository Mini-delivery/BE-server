package me.woodgeon.minidelivery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.woodgeon.minidelivery.domain.Order;
import me.woodgeon.minidelivery.dto.order.AddOrderRequest;
import me.woodgeon.minidelivery.mqtt.MqttGateway;
import me.woodgeon.minidelivery.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MqttGateway mqttGateway;
    private final ObjectMapper objectMapper;

    public Order save(AddOrderRequest request) {
        return orderRepository.save(request.toEntity());
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /*public Order getLatestOrder() {
        Order latestOrder = orderRepository.findLatestOrder();
        if (latestOrder != null) {
            return latestOrder;  // 최신 주문 반환
        } else {
            throw new NoSuchElementException("No order found");  // 주문이 없는 경우 예외 처리
        }
    }*/

    public void sendOrderToMqtt(Order order) throws JsonProcessingException {
        String orderJson = objectMapper.writeValueAsString(order);

        mqttGateway.sendToMqtt(orderJson);
    }
}
