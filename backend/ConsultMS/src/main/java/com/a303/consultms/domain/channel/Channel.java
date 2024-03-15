package com.a303.consultms.domain.channel;

import com.a303.consultms.domain.BaseEntity;
import com.a303.consultms.domain.consult.Consult;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Channel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private int channelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consult_id")
    private Consult consult;

    @Column(name = "member_id")
    private int memberId;

    @Column(name = "is_closed")
    private boolean isClosed;



    //생성 메서드
    public static Channel createChannel(Consult consult){
        Channel channel = new Channel();

        channel.setConsult(consult);

        return channel;
    }
}
