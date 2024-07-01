package dev.bassi.btgpactual.orderms.service;

import dev.bassi.btgpactual.orderms.dto.OrderCreatedEvent;
import dev.bassi.btgpactual.orderms.mapper.AutoOrderMapper;
import dev.bassi.btgpactual.orderms.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public void save(OrderCreatedEvent event){
        var entity = AutoOrderMapper.MAPPER.mapToOrderEntity(event);
        this.repository.save(entity);
    }
}
