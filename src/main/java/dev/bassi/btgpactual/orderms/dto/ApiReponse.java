package dev.bassi.btgpactual.orderms.dto;

import java.util.List;

public record ApiReponse<T>(List<T> data, PaginationResponse pagination) {
}
