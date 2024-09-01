package me.woodgeon.minidelivery.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "foodOrder", nullable = false)
    private String foodOrder;

    @Column(name = "address", nullable = false)
    private String address;

    @Builder
    public Order(String foodOrdered, String address) {
        this.foodOrder = foodOrdered;
        this.address = address;
    }
}
