package com.example.userserver.module.domain.product.dto.response;

import lombok.Builder;

@Builder
public record ProductRes(
        int productId,
        String name
) {

}
