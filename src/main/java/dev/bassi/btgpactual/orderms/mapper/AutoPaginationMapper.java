package dev.bassi.btgpactual.orderms.mapper;

import dev.bassi.btgpactual.orderms.dto.PaginationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface AutoPaginationMapper {

    AutoPaginationMapper MAPPER = Mappers.getMapper(AutoPaginationMapper.class);

    @Mapping(source = "number", target = "page")
    @Mapping(source = "size", target = "pageSize")
    @Mapping(source = "totalElements", target = "totalElements")
    @Mapping(source = "totalPages", target = "totalPages")
    PaginationResponse mapToOrderEntity(Page<?> page);
}
