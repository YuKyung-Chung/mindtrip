import { Tooltip, Button, Input } from '@nextui-org/react'
import { changeList } from '../../../store/store'
import { useDispatch } from "react-redux"
import LeftIcon from '../../../atoms/Icons/LeftIcon'
import UpIcon from '../../../atoms/Icons/UpIcon'
import DownIcon from '../../../atoms/Icons/DownIcon'
import SendIcon from '../../../atoms/Icons/SendIcon'
import { useState } from 'react'
import './Ballon.css'

// 채팅방

function Chatting() {
  const dispatch = useDispatch()

  // 추가정보 열고 닫고
  const [show, setShow] = useState<boolean>(false)

  return (
    <div>
      {/* 상단 */}
      <div className='relative flex items-center justify-between mb-5'>
        <Tooltip placement='bottom' content='뒤로가기'>
          <Button
            isIconOnly
            variant='light'
            onClick={() => dispatch(changeList(true))}
          ><LeftIcon /></Button>
        </Tooltip>
        <p>고민 제목</p>
        <Button
          isIconOnly
          size='sm'
          variant='light'
          onClick={() => setShow(!show)}
        >{show ? <UpIcon /> : <DownIcon />}</Button>
      </div>
      {/* 채팅방 */}
      <div className='h-[43vh] w-full p-1 overflow-scroll'>
        <OtherBallon message='어떤 고민인가요?'/>
        <MyBallon message='아니 진짜 들어보세요' />
        <MyBallon message='오늘 저녁으로 육회를 먹고싶은데' />
        <MyBallon message='어떨까요?' />
        <OtherBallon message='그걸 지금 고민이라고 올린거에요?'/>
        <OtherBallon message='완전 긴 텍스트 완전 긴 텍스트 완전 긴 텍스트 완전 긴 텍스트 완전 긴 텍스트 완전 긴 텍스트 완전 긴 텍스트 완전 긴 텍스트 완전 긴 텍스트 완전 긴 텍스트 완전 긴 텍스트 '/>
        <MyBallon message='너무하시네 그럴수도 있지ㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜ ' />
      </div>
      {/* 채팅 치는 곳 */}
      <div className='mt-3 flex justify-between items-center'>
        <Input variant='bordered' className='w-[88%]'/>
        <Button
          isIconOnly
          size='sm'
          variant='light'
        ><SendIcon/></Button>
      </div>
    {/* 추가정보 나오는 곳 */}
    {
      show && (
        <div 
          className='absolute top-[15%] 
            border-1 rounded-xl p-3 
            w-[90%] h-[40%]
            bg-white'
        >
          <p>고민제목</p>
          <div className='min-h-[60%] overflow-scroll'>
            <p className='text-sm'>고민내용</p>
          </div>
          <div className='flex justify-end'>
            <Button size='sm'>상대 내보내기</Button>
          </div>
        </div>
      )
    }
    </div>
  )
}

export default Chatting

type propsType = {
  message : string
}


// 남의 채팅
function OtherBallon({message}: propsType) {
  return(
    <div className='relative bg-[#f5a524] rounded-lg w-fit max-w-[90%] py-1 px-2 ml-4 my-2'>
      {message}
      <div className='absolute tail-left'/>
    </div>
  )
}

// 내 채팅
function MyBallon({message}: propsType) {
  return(
    <div className='flex justify-end'>
      <div className='relative bg-[#e5ceae] rounded-lg w-fit max-w-[90%] py-1 px-2 my-2 mr-1.5'>
      {message}
      <div className='absolute tail-right'/>
    </div>
    </div>
    
  )
}
