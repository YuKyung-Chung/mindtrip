package com.a303.consultms.domain.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "seq")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seq {

    @Id
    private String id;

    @Field(name = "seq")
    private int seq;
}
