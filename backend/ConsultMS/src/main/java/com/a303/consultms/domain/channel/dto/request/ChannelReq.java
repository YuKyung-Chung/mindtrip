package com.a303.consultms.domain.channel.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ChannelReq(
    int receiver,
    int sender
) {

}
