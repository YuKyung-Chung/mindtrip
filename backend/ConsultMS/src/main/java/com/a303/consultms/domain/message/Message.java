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

    //고민상담소 방 ID
    @Field(name = "consult_id")
    private int counsultId;

    @Field(name = "message_id")
    @NotNull
    private int messageId;

    @Field(name = "message_to")
    private String messageTo;

    @Field(name = "content")
    private String content;


}
