package me.woodgeon.minidelivery.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "foodOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "orderTime", nullable = false)
    private String orderTime;

    @Column(name = "foodName", nullable = false)
    private String foodName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "amount", nullable = false)
    private String amount;

    @Builder
    public Order(String orderTime, String foodName, String address, String amount) {
        this.orderTime = orderTime;
        this.foodName = foodName;
        this.address = address;
        this.amount = amount;
    }
}
