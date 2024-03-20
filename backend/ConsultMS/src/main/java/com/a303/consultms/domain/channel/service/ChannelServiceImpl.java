package com.a303.consultms.domain.channel.service;

import com.a303.consultms.domain.channel.Channel;
import com.a303.consultms.domain.channel.repository.ChannelRepository;
import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.consult.dto.request.ConsultUpdateReq;
import com.a303.consultms.domain.consult.dto.response.ConsultDetailRes;
import com.a303.consultms.domain.consult.repository.ConsultRepository;
import com.a303.consultms.domain.message.Message;
import com.a303.consultms.global.client.MemberClient;
import com.a303.consultms.global.exception.BaseExceptionHandler;
import com.a303.consultms.global.exception.code.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final ConsultRepository consultRepository;
    private final MemberClient memberClient;

    //고민상담소 입장(채널생성)
    @Override
    public String enterConsultingRoom(int consultId, int memberId) throws BaseExceptionHandler {

        consultException(consultId);
//        memberException(memberId);

        List<Message> messageList = new ArrayList<>();

        //채널 생성
        Channel channel = Channel.createChannel(consultId, memberId, false, false, messageList);

        channelRepository.save(channel);

        return channel.getId();
    }

    //회원 존재여부 유효성 검사
    private void memberException(int memberId) {
        //멤버 존재하는지 유효성 검사 추가하기
    }

    //고민상담소 존재여부 유효성 검사
    private void consultException(int consultId) {
        if (consultRepository.findConsultByConsultId(consultId) == null) {
            throw new BaseExceptionHandler(ErrorCode.NOT_FOUND_CONSULT_EXCEPTION);
        }
    }
}
