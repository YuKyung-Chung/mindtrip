import { useState } from 'react'
import { changeList, changeSelectedId } from '../../../store/chatSlice'
import { useDispatch } from "react-redux"

// 채팅방 리스트들이 들어가는 컴포넌트

function ChatList() {
  // 상단 탭 제어용 true면 나의 고민
  const [pickFirst, setpickFirst] = useState<boolean>(true)
  const [personalChatId, setPersonalChatId] = useState<string>("")

  return (
    <div className="h-[70vh]">
      <div className="flex h-[8vh] justify-between items-center text-center">
        <div className="w-1/2 hover:cursor-pointer" onClick={() => setpickFirst(true)}>나의 고민</div>
        <div className="w-1/2 hover:cursor-pointer" onClick={() => setpickFirst(false)}>내가 들어준 고민</div>
      </div>
      <div
        className="w-1/2 h-1 bg-[#f9c56f] rounded-md mb-2"
        style={{ transition: 'margin-left 0.3s ease', marginLeft: pickFirst ? 0 : '50%' }}
      />
        {
          pickFirst && (
            <div>
              <Chatting title='내가 쓴 고민들 제목' content='하이' alert={800} channelId='66021b7bb91c095bbc4f81d7'/>
              <Chatting title='여긴' content='내가 올렸던 고민들이' alert={2} channelId='66021b7bb91c095bbc4f81d7'/>
              <Chatting title='들어와요' content='하이' alert={2} channelId='66021b7bb91c095bbc4f81d7'/>
            </div>
          )
        }
        {
          pickFirst === false && (
            <div>
              <Chatting title='여긴 내가 들어준 고민' content='하이' alert={2} channelId='7'/>
              <Chatting title='고민 제목' content='여긴 내가 들어줄려고 참여했던 고민들이' alert={2} channelId='7'/>
              <Chatting title='고민 제목' content='들어올꺼야' alert={10219} channelId='7'/>
            </div>
          )
        }

    </div>
  )
}

export default ChatList

type propstype = {
  title: string,
  content: string,
  alert: number,
  channelId: string // channelId 추가
}

function Chatting({title, content, alert, channelId} :propstype) {
  const dispatch = useDispatch()

  const handleClick = (channelId : string) => {
    dispatch(changeList(false))
    dispatch(changeSelectedId(channelId))
  };

  return(
    <div className="relative border-b h-24 p-3 hover:bg-gray-100" onClick={() => handleClick(channelId)}>
      <p className="text-lg">{title}</p>
      <p className="text-sm overflow-hidden">{content}</p>
      <div className="absolute right-[5%] top-[30%] rounded-full bg-[#f2dec2] w-10 h-10 text-center">
        <p className="mt-2.5 text-sm">{alert > 300 ? '300+' : alert}</p>
      </div>
    </div>
  )
}
