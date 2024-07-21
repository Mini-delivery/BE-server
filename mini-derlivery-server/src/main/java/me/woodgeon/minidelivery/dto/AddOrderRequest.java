package me.woodgeon.minidelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.woodgeon.minidelivery.domain.Order;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddOrderRequest {

    private String foodOrder;
    private String address;

    public Order toEntity() {
        return Order.builder()
                .foodOrdered(foodOrder)
                .address(address)
                .build();
    }
}
