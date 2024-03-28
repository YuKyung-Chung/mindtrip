package com.a303.postitms.domain.reportPostit;

import com.a303.postitms.domain.BaseEntity;
import com.a303.postitms.domain.likePostit.LikePostit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "report_postit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportPostit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_postit_id")
    private int reportPostitId;

    @Column(name = "member_id")
    private int memberId;

    @Column(name = "postit_id")
    private String postitId;
    public static ReportPostit createReportPostit(String postitId, int memberId) {
        ReportPostit reportPostit = new ReportPostit();

        reportPostit.setPostitId(postitId);
        reportPostit.setMemberId(memberId);

        return reportPostit;
    }
}
