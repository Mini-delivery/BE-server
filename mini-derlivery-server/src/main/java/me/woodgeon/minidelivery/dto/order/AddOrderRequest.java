package me.woodgeon.minidelivery.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.woodgeon.minidelivery.domain.Order;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddOrderRequest {
    private String store_id;
    private String user_id;
    private String order_name;
    private String address;
    private int price;

    public Order toEntity() {
        return Order.builder()
                .store_id(store_id)
                .user_id(user_id)
                .order_name(order_name)
                .price(price)
                .address(address)
                .build();
    }
}

