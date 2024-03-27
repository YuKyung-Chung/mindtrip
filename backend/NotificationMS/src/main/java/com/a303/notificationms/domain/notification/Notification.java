package com.a303.notificationms.domain.notification;


import com.a303.notificationms.domain.BaseEntity;
import com.a303.notificationms.domain.domain.Domain;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
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
public class Notification extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_id")
	private int notificationId;

	private int memberId;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "domain_id")
	private Domain domain;

	private boolean isWritten;

	@Column(length = 100)
	private String content;

	public static Notification createNotification(int memberId, Domain domain, boolean isWritten,
		String content) {

		Notification notification = new Notification();
		notification.setMemberId(memberId);
		notification.setDomain(domain);
		notification.setWritten(isWritten);
		notification.setContent(content);

		return notification;
	}

}
