package com.a303.consultms.domain.message.dto;

import lombok.Builder;

@Builder
public record ChatMessage(
    String channelId,
    String sender,
    int memberId,
    String content
) {

}
