package com.a303.postitms.domain.postitTopic;


import com.a303.postitms.domain.MongoBaseEntity;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "postit_topic")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostitTopic extends MongoBaseEntity {

    @Id
    private String id;

    @Field(name = "topic")
    private String topic;

    @Field(name = "postit_date")
    private String postitDate;

    public static PostitTopic createPostitTopic(String topic, String postitDate) {
        PostitTopic postitTopic = new PostitTopic();

        postitTopic.setTopic(topic);
        postitTopic.setPostitDate(postitDate);

        return postitTopic;
    }
}
