package com.a303.consultms.domain.message.dto.request;

import lombok.Builder;

@Builder
public record MessageReq(
    int sender,
    String text
) {

}
