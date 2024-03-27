import axios, { AxiosResponse } from 'axios';

const readPersonalChat = (accessToken: string, personalChatId: number): void => {
    axios.delete(`https://i10a810.p.ssafy.io/api/studies/v1/alarm/personalChat/${personalChatId}`, {
    // axios.delete(`http://localhost:8080/api/studies/v1/alarm/personalChat/${personalChatId}`, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
    .then(() => {

    })
    .catch((err) => console.log(err))
}

const readChannelChat = (accessToken: string, channelId: number): void => {
    axios.delete(`https://i10a810.p.ssafy.io/api/studies/v1/alarm/channels/${channelId}`, {
    // axios.delete(`http://localhost:8080/api/studies/v1/alarm/channels/${channelId}`, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
    })
    .then(() => {

    })
    .catch((err) => console.log(err))
}

// 채팅방 얻기
const getPersonalChat = async (accessToken: string, personalChatId: number): Promise<any> => {
    try {
        const res: AxiosResponse = await axios.get(`https://i10a810.p.ssafy.io/api/personal-chat/v1/${personalChatId}`,{
            headers: {
                Authorization: `Bearer ${accessToken}`,
            }
        });
        readPersonalChat(accessToken, personalChatId);
        return res.data.result;
    } catch (err) {
        console.log(err);
        throw err;
    }
}

// 채팅방 얻기, 없으면 생성
const registPersonalChat = async (accessToken: string, receiver: number): Promise<any> => {
    try {
        const res: AxiosResponse = await axios.post(`https://i10a810.p.ssafy.io/api/personal-chat/v1`,{
            "receiver": receiver,
        }, {
            headers: {
                Authorization: `Bearer ${accessToken}`,
            }
        });
        return res.data.result;
    } catch (err) {
        console.log(err);
        throw err;
    }
}

const send = (stompClient: any, sender: string, message: string, personalChatId: number): void => {
    if (stompClient && stompClient.connected) {
        const msg = {
            sender: sender,
            text: message,
            personalChatId: personalChatId
        };
        stompClient.publish({
            destination: "/pub/api/chat/send/" + personalChatId,
            body: JSON.stringify(msg)
        });

    } else {
        alert("서버 오류로 인해 메시지 전송이 실패하였습니다!");
    }
};

const sendChannel = (accessToken: string, stompClient: any, message: any, channelId: number): void => {
    if (stompClient && stompClient.connected) {
        stompClient.publish({
            destination: "/pub/api/chat/channel/send/" + channelId,
            headers: {
                token: accessToken
            },
            body: JSON.stringify(message)
        });
    } else {
        alert("서버 오류로 인해 메시지 전송이 실패하였습니다!");
    }
};

const entranceChannel = (accessToken: string, stompClient: any, message: any, channelId: number): void => {
    if (stompClient && stompClient.connected) {
        stompClient.publish({
            destination: "/pub/api/chat/channel/enter/" + channelId,
            headers: {
                token: accessToken
            },
            body: JSON.stringify(message)
        });
    } else {
        alert("서버 오류로 인해 메시지 전송이 실패하였습니다!");
    }
};

export {
    registPersonalChat,
    getPersonalChat,
    send,
    sendChannel,
    entranceChannel,
    readPersonalChat,
    readChannelChat
}
