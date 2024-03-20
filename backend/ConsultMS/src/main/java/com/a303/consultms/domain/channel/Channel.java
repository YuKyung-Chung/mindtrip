package com.a303.consultms.domain.channel;

import com.a303.consultms.domain.BaseEntity;
import com.a303.consultms.domain.MongoBaseEntity;
import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.message.Message;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "channel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Channel extends MongoBaseEntity {

    @Id
    private String id;

    //고민상담소 방 ID
    @Field(name = "consult_id")
    private int counsultId;

    @Field(name = "member_id")
    private int memberId;

    @Field(name = "is_closed")
    private boolean isClosed = false; //기본값으로 false 설정

    @Field(name = "is_shared")
    private boolean isShared = false; //기본값으로 false 설정

    @DBRef
    private List<Message> messageList;

    public static Channel createChannel(int consultId, int memberId, boolean isClosed,
        boolean isShared, List<Message> message) {
        Channel channel = new Channel();

        channel.setCounsultId(consultId);
        channel.setMemberId(memberId);
        channel.setMessageList(message);
        channel.setClosed(isClosed);
        channel.setShared(isShared);

        return channel;
    }
}
