package com.a303.consultms.domain.consult;

import com.a303.consultms.domain.BaseEntity;
import com.a303.consultms.domain.channel.Channel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "member_id")
    private int memberId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "category_id")
    private int categoryId;

    // Channel - Consult 연관 관계
    @OneToMany(mappedBy = "consult", fetch = FetchType.LAZY)
    private List<Channel> channelList = new ArrayList<>();

    //생성 메서드
    public static Consult createConsult(String content, int categoryId) {
        Consult consult = new Consult();

        consult.setContent(content);
        consult.setCategoryId(categoryId);

        return consult;
    }

}
