package dev.bassi.btgpactual.orderms.dto;

import java.util.List;
import java.util.Map;

public record ApiReponse<T>(Map<String, Object> summary,
                            List<T> data,
                            PaginationResponse pagination) {
}
