package me.woodgeon.minidelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"me.woodgeon.minidelivery", "me.woodgeon.minidelivery.mqtt"})
public class MiniDeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniDeliveryApplication.class, args);
    }
}
