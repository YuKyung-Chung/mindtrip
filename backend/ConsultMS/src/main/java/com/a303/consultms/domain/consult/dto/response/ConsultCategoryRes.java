package com.a303.consultms.domain.consult.dto.response;

import lombok.Builder;

@Builder
public record ConsultCategoryRes(
    int categoryId,
    String categoryName
) {

}
