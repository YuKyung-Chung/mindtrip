package com.a303.consultms.domain.channel.dto.response;

import lombok.Builder;

@Builder
public record ChannelInfoRes(
    String channelId,
    int consultId,
    int memberId,
    String title,
    String content,
    boolean isClosed,
    boolean isShared
) {

}
