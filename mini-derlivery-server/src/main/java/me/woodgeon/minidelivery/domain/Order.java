package me.woodgeon.minidelivery.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false)
    private int order_id;

    @Column(name = "store_id", nullable = false)
    private int store_id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "order_tp_cd", nullable = false)
    private String order_tp_cd;

    @Column(name = "order_date", nullable = false)
    private Date order_date;

    @Column(name = "use_yn", nullable = false)
    private String use_yn;

    public Order(int store_id, String user_id, String order_tp_cd, Date order_date, String use_yn) {
        this.store_id = store_id;
        this.user_id = user_id;
        this.order_tp_cd = order_tp_cd;
        this.order_date = order_date;
        this.use_yn = use_yn;
    }

}

