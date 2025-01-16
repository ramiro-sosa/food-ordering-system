package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCratedPaymentRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class OrderCreatedEventApplicationListener {

    private final OrderCratedPaymentRequestMessagePublisher orderCratedPaymentRequestMessagePublisher;

    public OrderCreatedEventApplicationListener(OrderCratedPaymentRequestMessagePublisher orderCratedPaymentRequestMessagePublisher) {
        this.orderCratedPaymentRequestMessagePublisher = orderCratedPaymentRequestMessagePublisher;
    }

    @TransactionalEventListener
    void process(OrderCreatedEvent orderCreatedEvent) {
        orderCratedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
    }
}
