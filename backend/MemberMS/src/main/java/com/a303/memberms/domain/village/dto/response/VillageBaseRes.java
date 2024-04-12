package com.a303.memberms.domain.village.dto.response;

import lombok.Builder;

@Builder
public record VillageBaseRes(
	Integer villageId,
	String villageName
) {

}
