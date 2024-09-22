package me.woodgeon.minidelivery.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false)
    private int order_id;

    @Column(name = "store_id", nullable = false)
    private String store_id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "order_name", nullable = false)
    private String order_name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "order_date", nullable = false)
    private String order_date;

    public Order(String store_id, String user_id, String order_name, int price, String address) {
        this.store_id = store_id;
        this.user_id = user_id;
        this.order_name = order_name;
        this.price = price;
        this.address = address;
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        // 원하는 형식의 DateTimeFormatter 생성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 H시 m분");

        // 포맷팅된 문자열로 저장
        this.order_date = now.format(formatter);
    }
}

