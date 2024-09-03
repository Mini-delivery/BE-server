package me.woodgeon.minidelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import me.woodgeon.minidelivery.domain.Order;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddOrderRequest {
    private String orderTime;
    private String foodName;
    private String address;
    private String amount;

    public Order toEntity() {
        return Order.builder()
                .orderTime(orderTime)
                .foodName(foodName)
                .address(address)
                .amount(amount)
                .build();
    }
}

