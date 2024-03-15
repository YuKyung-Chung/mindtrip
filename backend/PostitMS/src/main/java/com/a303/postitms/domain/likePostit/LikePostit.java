package com.a303.postitms.domain.likePostit;

import com.a303.postitms.domain.BaseEntity;
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
@Entity(name = "like_postit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikePostit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_postit_id")
    private int likePostitId;

    @Column(name = "member_id")
    private int memberId;

    @Column(name = "postit_id")
    private int postitId;

    public static LikePostit createLikePostit(int postitId, int memberId) {
        LikePostit likePostit = new LikePostit();

        likePostit.setPostitId(postitId);
        likePostit.setMemberId(memberId);

        return likePostit;
    }
}
