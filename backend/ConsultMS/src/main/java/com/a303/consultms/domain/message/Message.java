package com.a303.consultms.domain.message;

import com.a303.consultms.domain.MongoBaseEntity;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Builder;
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
    private String messageId;

    @Field(name = "sender")
    private Map<String, String> sender;

    @Field(name = "text")
    private String text;

    static public Message createMessage(Map<String, String> sender, String text){
        Message message = new Message();

        message.setSender(sender);
        message.setText(text);

        return message;
    }

}
