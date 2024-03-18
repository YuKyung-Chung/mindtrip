package com.a303.memberms.domain.reportMember;

import com.a303.memberms.domain.member.Member;
import com.a303.memberms.domain.member.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_member_id")
	private int reporterMemberId;

	// reporter-member 와의 관계 설정
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reporter")
	private Member reporter;

	// reportee-member 와의 관계 설정
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reportee")
	private Member reportee;

}
