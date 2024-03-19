import { useState } from 'react'
import { Button, ButtonGroup, Tooltip, Card  } from "@nextui-org/react";
import { useNavigate } from 'react-router-dom';
import OtherConsult from '../../components/Consult/OtherConsult';
import { useSelector, useDispatch } from "react-redux";
import { RootState } from './../../store/store'
import { toggleOpen } from '../../store/store';
import ChatIcon from './../../atoms/Icons/ChatIcon'
import XIcon from '../../atoms/Icons/XIcon';
import Chat from '../../components/Consult/Chat/Chat';

function ConsultOther() {
  const dispatch = useDispatch()
  const navigate = useNavigate()

  // 선택된 카테고리 제어용
  const [selectedIndex, setSelectedIndex] = useState<number>(0)
  const changeIndex = (num: number) => () => {
    setSelectedIndex(num);
  }

  // 채팅 관련 가져오기
  let chat = useSelector((state:RootState)=> state.chat)

  const categories: string[] = ['전체', '연애/결혼', '진로/취업', '기타']

  return (
    <div className="w-full lg:w-3/4 mx-auto h-screen pt-5">
      <div className="py-5 px-3 min-h-[40%]">
        <p className="text-2xl hover:cursor-pointer" onClick={() => navigate('/consult/other')}>다른 사람들의 고민 보기<span className='text-sm'>(오늘 남은 횟수: 5)</span></p>
        <div className="sm:flex sm:justify-between mt-4 mb-2">
          {/* 카테고리들 */}
          <ButtonGroup>
            {
              categories.map((category: string, idx: number) => {
                return (
                  <Button
                    key={idx}
                    variant={idx === selectedIndex ? 'solid' : 'ghost'}
                    onClick={changeIndex(idx)} className='text-black'
                  >
                    {category}
                  </Button>
                )
              })
            }
          </ButtonGroup>
        </div>
        <div className='mt-2 flex-col'>
          <div className='w-full p-2 h-44'>
            <OtherConsult/>
          </div>
          <div className='w-full p-2 h-44'>
            <OtherConsult/>
          </div>
          <div className='w-full p-2 h-44'>
            <OtherConsult/>
          </div>
          <div className='w-full p-2 h-44'>
            <OtherConsult/>
          </div>
          <div className='w-full p-2 h-44'>
            <OtherConsult/>
          </div>
        </div>
      </div>
      {/* 채팅 창 여는 버튼 */}
      <Tooltip content={chat.isOpen ? '닫기' : '대화 확인하기'} placement={chat.isOpen ? 'right' : 'top'}>
        <Button
          isIconOnly
          size='lg'
          radius='full'
          variant={chat.isOpen ? 'solid' : 'faded'}
          onClick={() => dispatch(toggleOpen())}
          className='fixed bottom-10 right-[4%] shadow-xl'
        >
          {chat.isOpen ? <XIcon/> : <ChatIcon />}
        </Button>
      </Tooltip>
      {/* 채팅창 */}
      <Card 
        style={{
          display: chat.isOpen ? 'block' : 'none',
        }}
        className='fixed top-[23%] right-[5%] w-[80%] h-[70%]
          sm:top-[20%] w-96 h-[65%] p-5'
      >
        <Chat />
      </Card>
    </div>
  )
}

export default ConsultOther