package com.example.productserver.module.domain.product.dto.response;

import lombok.Builder;

@Builder
public record ProductRes(
        int productId,
        String name
) {

}
