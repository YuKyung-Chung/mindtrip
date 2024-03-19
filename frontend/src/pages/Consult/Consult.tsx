import { useState, useEffect } from 'react'
import { Button, ButtonGroup, Input, Tooltip, Card } from "@nextui-org/react";
import OtherConsult from '../../components/Consult/OtherConsult';
import SharedConsult from '../../components/Consult/SharedConsult';
import BackButton from '../../atoms/buttons/backbtn';
import { useNavigate } from 'react-router-dom';
import SearchIcon from '../../atoms/Icons/SearchIcon';
import ChatIcon from './../../atoms/Icons/ChatIcon'
import XIcon from '../../atoms/Icons/XIcon';
import { toggleOpen } from '../../store/store';
import { useSelector, useDispatch } from "react-redux";
import { RootState } from './../../store/store'
import Chat from '../../components/Consult/Chat/Chat';

// 고민상담소 첫 페이지

function Consult() {
  const dispatch = useDispatch()

  // 채팅창 관련 가져오기
  let chat = useSelector((state:RootState)=> state.chat)

  // 처음 들어오면 채팅창 닫혀있게
  useEffect(() => {
    if (chat.isOpen) {
      dispatch(toggleOpen())
    }
  }, [])

  return (
    <div>
      {/* 전체페이지 */}
      <div className="w-full md:w-4/5 mx-auto h-screen pt-5">
        {/* 다른 사람의 고민 */}
        <Others />
        {/* 공유된 고민들 */}
        <Shared />
        {/* 뒤로가기 버튼 */}
        <div className='hidden sm:block'>
          <BackButton />
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

export default Consult

// 다른 사람들의 고민
function Others() {
  const navigate = useNavigate()

  // 선택된 카테고리 제어용
  const [selectedIndex, setSelectedIndex] = useState<number>(0)
  const changeIndex = (num: number) => () => {
    setSelectedIndex(num);
  }

  const categories: string[] = ['전체', '연애/결혼', '진로/취업', '기타']

  return (
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
        <p
          className='underline underline-offset-4 hover:cursor-pointer hidden sm:block'
          onClick={() => navigate('/consult/other')}
        >더보러가기</p>
      </div>
      <div className='mt-2 flex overflow-x-auto'>
        <div className="w-44 h-48 m-2 min-w-44">
          <OtherConsult />
        </div>
        <div className="w-44 h-48 m-2 min-w-44">
          <OtherConsult />
        </div>
        <div className="w-44 h-48 m-2 min-w-44">
          <OtherConsult />
        </div>
        <div className="w-44 h-48 m-2 min-w-44">
          <OtherConsult />
        </div>
        <div className="w-44 h-48 m-2 min-w-44">
          <OtherConsult />
        </div>
      </div>
    </div>
  )
}


// 공유된 고민
function Shared() {
  const navigate = useNavigate()

  // 선택된 카테고리 제어용
  const [selectedIndex, setSelectedIndex] = useState<number>(0)
  const changeIndex = (num: number) => () => {
    setSelectedIndex(num);
  }

  const categories: string[] = ['전체', '연애/결혼', '진로/취업', '기타']

  return (
    <div className="px-3 min-h-[40%] mt-3">
      <p className="text-2xl hover:cursor-pointer" onClick={() => navigate('/consult/shared')}>공유된 고민 상담들 둘러보기</p>
      <div className="sm:flex sm:justify-between sm:items-center">
        <div className="md:flex md:items-center w-5/6 mt-4 mb-2">
          {/* 카테고리들 */}
          <ButtonGroup className='md:mr-5'>
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
          <Input
            isClearable
            variant='underlined'
            placeholder='검색'
            size='sm'
            startContent={
              <SearchIcon />
            }
            className='mt-5 md:mt-0 w-48'
          />
        </div>
        <p
          className='underline underline-offset-4 hover:cursor-pointer hidden sm:block'
          onClick={() => navigate('/consult/shared')}
        >더보러가기</p>
      </div>
      <div className='mt-2 flex overflow-x-auto'>
        <div className="w-44 m-2 min-w-44 h-44">
          <SharedConsult />
        </div>
        <div className="w-44 m-2 min-w-44">
          <SharedConsult />
        </div>
        <div className="w-44 m-2 min-w-44">
          <SharedConsult />
        </div>
        <div className="w-44 m-2 min-w-44">
          <SharedConsult />
        </div>
        <div className="w-44 m-2 min-w-44">
          <SharedConsult />
        </div>
        <div className="w-44 m-2 min-w-44">
          <SharedConsult />
        </div>
        <div className="w-44 m-2 min-w-44">
          <SharedConsult />
        </div>

      </div>
    </div>
  )
}
