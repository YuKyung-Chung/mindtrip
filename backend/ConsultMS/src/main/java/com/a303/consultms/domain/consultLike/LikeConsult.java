package com.a303.consultms.domain.consultLike;

import com.a303.consultms.domain.BaseEntity;
import com.a303.consultms.domain.consult.Consult;
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
@Entity(name = "like_consult")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeConsult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_consult_id")
    private int likeConsultId;

    @Column(name = "member_id")
    private int memberId;

    @Column(name = "consult_id")
    private int consultId;

    //생성 메서드
    public static LikeConsult createConsultLike(int consultId, int memberId) {
        LikeConsult likeConsult = new LikeConsult();

        likeConsult.setConsultId(consultId);
        likeConsult.setMemberId(memberId);

        return likeConsult;
    }
}
