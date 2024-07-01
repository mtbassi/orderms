package dev.bassi.btgpactual.orderms.dto;

import java.math.BigDecimal;

public record OrderItemEvent(String product,
                             Integer quantity,
                             BigDecimal price) {
}
