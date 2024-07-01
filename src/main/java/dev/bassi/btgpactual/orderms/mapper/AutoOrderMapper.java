package dev.bassi.btgpactual.orderms.mapper;

import dev.bassi.btgpactual.orderms.dto.OrderCreatedEvent;
import dev.bassi.btgpactual.orderms.dto.OrderResponse;
import dev.bassi.btgpactual.orderms.entity.OrderEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Optional;

@Mapper
public interface AutoOrderMapper {

    AutoOrderMapper MAPPER = Mappers.getMapper(AutoOrderMapper.class);

    @Mapping(target = "totalOrderValue", ignore = true)
    OrderEntity mapToOrderEntity(OrderCreatedEvent event);

    OrderResponse mapToOrderResponse(OrderEntity entity);

    @AfterMapping
    default void calculatesTotalOrderValue(@MappingTarget OrderEntity entity) {
        BigDecimal total = Optional.ofNullable(entity.getItems())
                .map(items -> items.stream()
                        .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .orElse(BigDecimal.ZERO);
        entity.setTotalOrderValue(total);
    }
}
