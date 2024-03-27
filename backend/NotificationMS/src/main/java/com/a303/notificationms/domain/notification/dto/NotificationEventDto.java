package com.a303.notificationms.domain.notification.dto;

import lombok.Builder;

@Builder
public record NotificationEventDto(
	String eventType,
	int memberId // 전체 전송시 -1, 그외에는 특정인 아이디
) {

}
