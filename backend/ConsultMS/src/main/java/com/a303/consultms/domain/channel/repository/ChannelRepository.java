package com.a303.consultms.domain.channel.repository;

import com.a303.consultms.domain.channel.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChannelRepository extends MongoRepository<Channel, String> {


}
