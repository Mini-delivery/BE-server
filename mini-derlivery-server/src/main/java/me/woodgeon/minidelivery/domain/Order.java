package me.woodgeon.minidelivery.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="foodOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "order_time", nullable = false)
    private String order_time;

    @Column(name = "menu", nullable = false)
    private String menu;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Builder
    public Order(String order_time, String menu, String address, int amount) {
        this.order_time = order_time;
        this.menu = menu;
        this.address = address;
        this.amount = amount;
    }
}
