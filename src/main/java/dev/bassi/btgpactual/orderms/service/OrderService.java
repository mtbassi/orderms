package dev.bassi.btgpactual.orderms.service;

import dev.bassi.btgpactual.orderms.dto.OrderCreatedEvent;
import dev.bassi.btgpactual.orderms.dto.OrderResponse;
import dev.bassi.btgpactual.orderms.mapper.AutoOrderMapper;
import dev.bassi.btgpactual.orderms.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public void save(OrderCreatedEvent event) {
        var entity = AutoOrderMapper.MAPPER.mapToOrderEntity(event);
        this.repository.save(entity);
    }

    public Page<OrderResponse> findAllCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = this.repository.findAllByCustomerId(customerId, pageRequest);
        return orders.map(AutoOrderMapper.MAPPER::mapToOrderResponse);
    }
}
