package com.a303.consultms.domain.consult;

import com.a303.consultms.domain.BaseEntity;
import com.a303.consultms.domain.consultLike.LikeConsult;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.*;


@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Consult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consult_id")
    private int consultId;

    @NotNull
    @Column(name = "category_id")
    private int categoryId;

    @NotNull
    @Column(name = "member_id")
    private int memberId;

    @NotNull
    @Column(name = "nickname")
    private String nickname;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "content")
    private String content;

    @Column(name = "is_closed")
    private boolean isClosed = false;

    @Column(name = "is_shared")
    private boolean isShared = false;

    @Column(name = "can_like")
    private boolean canLike = true;

    @Column(name = "like_count")
    private int likeCount = 0;

    @Column(name = "channel_id") // 채널의 id를 저장할 필드 추가
    private String channelId;

    //생성 메서드
    public static Consult createConsult(int memberId, String nickname, String title, String content,
        int categoryId) {
        Consult consult = new Consult();

        consult.setMemberId(memberId);
        consult.setNickname(nickname);
        consult.setCategoryId(categoryId);
        consult.setTitle(title);
        consult.setContent(content);

        return consult;
    }

}
