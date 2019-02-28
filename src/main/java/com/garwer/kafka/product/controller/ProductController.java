package com.garwer.kafka.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Garwer
 * @Date: 19/2/26 下午10:43
 * @Version 1.0
 */

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/sendMsg")
    public void sendMsg(@RequestParam String msg) {
        ListenableFuture future = kafkaTemplate.send("garwer", msg);
        future.addCallback(o -> log.info("发送消息:{} success",msg), Throwable::printStackTrace);
    }
}
