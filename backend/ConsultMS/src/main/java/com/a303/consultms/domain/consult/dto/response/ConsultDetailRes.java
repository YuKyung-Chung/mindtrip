package com.a303.consultms.domain.consult.dto.response;

import lombok.Builder;

@Builder
public record ConsultDetailRes(

    int consultId,
    int memberId,
    String nickname,
    String title,
    String content,
    int categoryId,
    boolean isClosed,
    boolean isShared,
    boolean canLike,
    int likeCount,
    String channelId
) {

}
