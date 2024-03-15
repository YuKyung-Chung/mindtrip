package com.a303.postitms.domain.postitTopic;


import com.a303.postitms.domain.MongoBaseEntity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Document(collection = "postit_topic")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostitTopic extends MongoBaseEntity {

    @Id
    private String id;

    @Field(name = "topic")
    private String topic;

    @Field(name = "postit_list")
    @DBRef
    private List<String> postitList;

    @Field(name = "postit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postitDate;

    public static PostitTopic createPostitTopic(String topic, Date postitDate) {
        PostitTopic postitTopic = new PostitTopic();

        postitTopic.setTopic(topic);
        postitTopic.setPostitList(new ArrayList<>());
        postitTopic.setPostitDate(postitDate);

        return postitTopic;
    }
}
