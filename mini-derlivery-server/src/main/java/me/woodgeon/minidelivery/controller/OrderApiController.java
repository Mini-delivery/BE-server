package me.woodgeon.minidelivery.controller;

import lombok.RequiredArgsConstructor;
import me.woodgeon.minidelivery.domain.Order;
import me.woodgeon.minidelivery.dto.AddOrderRequest;
import me.woodgeon.minidelivery.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<Order> addOrder(@RequestBody AddOrderRequest request) {
        Order savedOrder = orderService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedOrder);
    }

    // 데이터 리턴하는 PostMapping 만들기
    //http://127.0.0.1/8080/api/orders
}
