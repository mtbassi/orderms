package dev.bassi.btgpactual.orderms.listener;

import dev.bassi.btgpactual.orderms.configuration.RabbitMqConfiguration;
import dev.bassi.btgpactual.orderms.dto.OrderCreatedEvent;
import dev.bassi.btgpactual.orderms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCreatedListener {

    private final OrderService orderService;

    @RabbitListener(queues = RabbitMqConfiguration.ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        this.orderService.save(message.getPayload());
    }
}
