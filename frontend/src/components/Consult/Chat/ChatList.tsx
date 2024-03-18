import { useState } from 'react'
import Chatting from './Chatting'

function ChatList() {
  // 상단 탭 제어용 true면 나의 고민
  const [pickFirst, setpickFirst] = useState<boolean>(true)

  return (
    <div className="h-[70vh]">
      <div className="flex h-[8vh] justify-between items-center text-center">
        <div className="w-1/2 hover:cursor-pointer" onClick={() => setpickFirst(true)}>나의 고민</div>
        <div className="w-1/2 hover:cursor-pointer" onClick={() => setpickFirst(false)}>내가 들어준 고민</div>
      </div>
      <div
        className="w-1/2 h-1 bg-[#f9c56f] rounded-md"
        style={{ transition: 'margin-left 0.3s ease', marginLeft: pickFirst ? 0 : '50%' }}
      />
        {
          pickFirst && (
            <div>
              <Chatting title='내가 쓴 고민들 제목' content='하이' alert={800}/>
              <Chatting title='여긴' content='내가 올렸던 고민들이' alert={2} />
              <Chatting title='들어와요' content='하이' alert={2} />
            </div>
          )
        }
        {
          pickFirst === false && (
            <div>
              <Chatting title='여긴 내가 들어준 고민' content='하이' alert={2} />
              <Chatting title='고민 제목' content='여긴 내가 들어줄려고 참여했던 고민들이' alert={2} />
              <Chatting title='고민 제목' content='들어올꺼야' alert={10219} />
            </div>
          )
        }

    </div>
  )
}

export default ChatList