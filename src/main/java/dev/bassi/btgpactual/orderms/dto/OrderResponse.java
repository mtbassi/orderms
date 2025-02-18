package dev.bassi.btgpactual.orderms.dto;

import java.math.BigDecimal;

public record OrderResponse(Long orderId,
                            Long customerId,
                            BigDecimal totalOrderValue) {
}
