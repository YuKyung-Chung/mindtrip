package com.a303.postitms.domain.postit;

import com.a303.postitms.domain.MongoBaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "postit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Postit extends MongoBaseEntity {
    @Id
    private String id;

    @Field(name = "content")
    private String content;

    @Field(name="member_id")
    private int memberId;

    @Field(name="topic")
    private String topic;

    @Field(name="report_count")
    private int reportCount;

    @Field(name="like_count")
    private int likeCount;

    public static Postit createPostit(String content, int memberId, String topic) {
        Postit postit = new Postit();

        postit.setContent(content);
        postit.setMemberId(memberId);
        postit.setTopic(topic);
        postit.setReportCount(0);
        postit.setLikeCount(0);

        return postit;
    }
}
