package com.a303.consultms.domain.consult.dto.response;

import lombok.Builder;

@Builder
public record ConsultDetailRes(

    int consultId,
    int memberId,
    String title,
    String content,
    int categoryId
) {

}
