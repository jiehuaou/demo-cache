package com.example.cache.controller;

import com.example.cache.provider.AnyDataProvider;
import com.example.cache.provider.CustomerProvider;
import com.example.cache.provider.MessageProvider;
import com.example.cache.provider.OrderProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
public class MyController {

    @Autowired
    CustomerProvider customerProvider;

    @Autowired
    OrderProvider orderProvider;

    @Autowired
    AnyDataProvider anyData;

    @Autowired
    MessageProvider messageProvider;

    @RequestMapping("/customer/{id}")
    public Mono<String> customer(@PathVariable("id") Long id) {
        log.info("Rx client /customer/{}", id);
        return Mono.just(customerProvider.getCustomer(id));
    }

    @RequestMapping("/order/{id}")
    public Mono<String> order(@PathVariable("id") Long id) {
        log.info("Rx client /order/{}", id);

        return Mono.just(orderProvider.getOrder(id));
    }

    @RequestMapping("/any/{id}")
    public Mono<String> any(@PathVariable("id") Long id) {
        log.info("Rx client /any/{}", id);

        return Mono.just(anyData.getAny(id));
    }

    @RequestMapping("/msg/{id}")
    public Mono<String> msg(@PathVariable("id") Long id) {
        log.info("Rx client /msg/{}", id);

        return Mono.just(messageProvider.getMessage(id));
    }

    @RequestMapping("/notify/{id}")
    public Mono<String> notify(@PathVariable("id") Long id) {
        log.info("Rx client /notify/{}", id);

        return Mono.just(messageProvider.getNotify(id));
    }
}
