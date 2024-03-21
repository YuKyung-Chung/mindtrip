package com.a303.consultms.domain.channel;

import com.a303.consultms.domain.BaseEntity;
import com.a303.consultms.domain.MongoBaseEntity;
import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.message.Message;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
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
    @Field(name = "channel_id")
    private String channelId;

    //고민상담소 방 ID
    @Field(name = "consult_id")
    private int counsultId;

    @Field(name = "receiver")
    private Map<String, String> receiver;

    @Field(name ="sender")
    private Map<String, String> sender;

    @Field(name = "is_closed")
    private boolean isClosed = false; //기본값으로 false 설정

    @Field(name = "is_shared")
    private boolean isShared = false; //기본값으로 false 설정

    @DBRef
    private List<Message> messageList;

    public static Channel createChannel(Map<String, String> receiver, Map<String, String> sender, List<Message> message) {
        Channel channel = new Channel();

//        channel.setCounsultId(consultId);
        channel.setReceiver(receiver);
        channel.setSender(sender);
        channel.setMessageList(message);

        return channel;
    }
}
