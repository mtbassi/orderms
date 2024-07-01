package dev.bassi.btgpactual.orderms.controller;

import dev.bassi.btgpactual.orderms.dto.ApiReponse;
import dev.bassi.btgpactual.orderms.dto.OrderResponse;
import dev.bassi.btgpactual.orderms.mapper.AutoPaginationMapper;
import dev.bassi.btgpactual.orderms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping("/costumer/{customerId}/orders")
    public ResponseEntity<ApiReponse<OrderResponse>> listOrders(@PathVariable("customerId") Long customerId,
                                                                @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var pageResponse = this.service.findAllCustomerId(customerId, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(new ApiReponse<>(pageResponse.getContent(), AutoPaginationMapper.MAPPER.mapToOrderEntity(pageResponse)));
    }
}
