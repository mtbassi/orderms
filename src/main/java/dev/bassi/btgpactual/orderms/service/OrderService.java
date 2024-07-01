package dev.bassi.btgpactual.orderms.service;

import dev.bassi.btgpactual.orderms.dto.OrderCreatedEvent;
import dev.bassi.btgpactual.orderms.dto.OrderResponse;
import dev.bassi.btgpactual.orderms.mapper.AutoOrderMapper;
import dev.bassi.btgpactual.orderms.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final MongoTemplate mongoTemplate;

    public void save(OrderCreatedEvent event) {
        var entity = AutoOrderMapper.MAPPER.mapToOrderEntity(event);
        this.repository.save(entity);
    }

    public Page<OrderResponse> findAllCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = this.repository.findAllByCustomerId(customerId, pageRequest);
        return orders.map(AutoOrderMapper.MAPPER::mapToOrderResponse);
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("totalOrderValue").as("total")
        );
        var response = this.mongoTemplate.aggregate(aggregations, "orders", Document.class);
        return new BigDecimal(Objects.requireNonNull(response.getUniqueMappedResult()).get("total").toString());
    }
}
