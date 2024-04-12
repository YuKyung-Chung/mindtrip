package com.a303.consultms.domain.channel.dto.response;

import com.a303.consultms.domain.member.dto.response.MemberBaseRes;
import lombok.Builder;

@Builder
public record ChannelRes(
    String channelId,
    MemberBaseRes receiver,
    MemberBaseRes sender
) {

}
