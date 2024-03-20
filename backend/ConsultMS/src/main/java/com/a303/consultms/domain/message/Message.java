package com.a303.consultms.domain.message;

import com.a303.consultms.domain.MongoBaseEntity;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@Document(collection = "message")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends MongoBaseEntity {

    @Id
    private String id;

    @Field(name = "channel_id")
    private String channelId;

    @Field(name = "sender")
    private String sender;

    @Field(name = "member_id")
    private int memberId;

    @Field(name = "content")
    private String content;

    static public Message createMessage(String sender, String content) {
        Message message = new Message();

        message.setSender(sender);
        message.setContent(content);

        return message;
    }
}
