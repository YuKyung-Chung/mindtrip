package com.a303.consultms.domain.message;

import com.a303.consultms.domain.MongoBaseEntity;

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

    @Field(name = "consult_id")
    private int counsultId;

    @Field(name = "message_id")
    private int messageId;

    @Field(name = "message_to")
    private String messageTo;

    @Field(name = "content")
    private String content;


}
