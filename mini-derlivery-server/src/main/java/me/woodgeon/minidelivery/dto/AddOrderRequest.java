package me.woodgeon.minidelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.woodgeon.minidelivery.domain.Order;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddOrderRequest {

    private String order_time;
    private String menu;
    private String address;
    private int amount;

    public Order toEntity() {
        return Order.builder()
                .order_time(order_time)
                .menu(menu)
                .address(address)
                .amount(amount)
                .build();
    }
}
