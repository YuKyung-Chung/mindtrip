package com.a303.notificationms.domain.notification.dto.response;

import lombok.Builder;

@Builder
public record CountNotificationMessageRes(
	String type,
	Long count
) {

}
