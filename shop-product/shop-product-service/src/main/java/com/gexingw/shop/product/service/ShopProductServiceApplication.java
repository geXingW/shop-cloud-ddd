package com.gexingw.shop.product.service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CountDownLatch;

/**
 * shop-cloud-ddd.
 *
 * @author GeXingW
 * @date 2023/11/12 15:59
 */
@EnableDubbo
@EnableAsync
@SpringBootApplication
public class ShopProductServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ShopProductServiceApplication.class, args);
        new CountDownLatch(1).await();
    }

}
