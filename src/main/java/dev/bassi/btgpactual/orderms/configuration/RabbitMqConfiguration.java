package dev.bassi.btgpactual.orderms.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    public static final String ORDER_CREATED_QUEUE = "btg-pactual-order-created";
}
