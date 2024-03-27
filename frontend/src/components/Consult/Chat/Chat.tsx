import { useEffect } from 'react'
import { changeList } from './../../../store/chatSlice';
import { useSelector, useDispatch } from "react-redux";
import { RootState } from './../../../store/store'
import ChatLists from './ChatList';
import Chatting from './Chatting'

// 채팅 안에 들어가는 전체 틀

function Chat() {
  const dispatch = useDispatch()

  // redux에서 변수 가져오기
  let chat = useSelector((state:RootState)=> state.chat)


  


  // 처음 열면 무조건 리스트부터보이게 하자
  useEffect(() => {
    dispatch(changeList(true))
  }, [])
  return(
    <div>
      {
        chat.isList ? <ChatLists /> : <Chatting/>
      }
    </div>
  )
}

export default Chat