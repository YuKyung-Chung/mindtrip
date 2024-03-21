import { useState, useEffect } from 'react'
import { Button, Input, Tooltip, Card, Select, SelectItem } from "@nextui-org/react";
import OtherConsult from '../../components/Consult/OtherConsult';
import SharedConsult from '../../components/Consult/SharedConsult';
import Chat from '../../components/Consult/Chat/Chat';
import Homebtn from '../../atoms/buttons/homebtn'
import { useNavigate } from 'react-router-dom';
import SearchIcon from '../../atoms/Icons/SearchIcon';
import ChatIcon from './../../atoms/Icons/ChatIcon'
import XIcon from '../../atoms/Icons/XIcon';

import { toggleOpen } from '../../store/chatSlice';
import { useSelector, useDispatch } from "react-redux";
import { RootState } from './../../store/store'
import { getConsultCategory } from '../../store/consultSlice';
import { getConsults, getCategory } from './../../api/consults'
import { consultType, categoryType } from '../../types/DataTypes';

// 고민상담소 첫 페이지

function Consult() {
  const dispatch = useDispatch()

  // 채팅창 관련 가져오기
  let chat = useSelector((state: RootState) => state.chat)

  // 카테고리 받기
  let category = useSelector((state: RootState) => state.consultSlice.category)

  useEffect(() => {
    // 처음 들어오면 채팅창 닫혀있게
    if (chat.isOpen) {
      dispatch(toggleOpen())
    }

    // 처음 들어올 때 카테고리 목록 가져오기
    const fetchCategory = async () => {
      try {
        let tempCategory: categoryType[] = await getCategory()
        dispatch(getConsultCategory(tempCategory))
      } catch (err) {
        console.log(err)
      }
    }
    if (category.length === 0) {
      fetchCategory()
    }
  }, [])

  return (
    <div>
      {/* 전체페이지 */}
      <div className="w-full md:w-4/5 mx-auto h-screen pt-5">
        {
          category != null ? (
            <div>
              {/* 다른 사람의 고민 */}
              <Others />
              {/* 공유된 고민들 */}
              <Shared />
              {/* 뒤로가기 버튼 */}
              <div className='hidden md:block'>
                <Homebtn />
              </div>
            </div>
          ) : null
        }
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
          {chat.isOpen ? <XIcon /> : <ChatIcon />}
        </Button>
      </Tooltip>
      {/* 채팅창 */}
      <Card
        style={{
          display: chat.isOpen ? 'block' : 'none',
        }}
        className='fixed top-[23%] right-[5%] w-[80%] h-[70%]
          sm:top-[20%] w-96 h-[65%] p-5 z-10'
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

  // 카테고리 받기
  let category = useSelector((state: RootState) => state.consultSlice.category)

  // 다른사람들의 고민List
  const [otherConsults, setOtherConsult] = useState<consultType[]>([])

  // 선택된 카테고리
  const [selectedCategory, setSelectedCategory] = useState<categoryType | null>(null)
  const handleCategory = (e: any) => {
    setSelectedCategory(e.target.value)
    console.log(selectedCategory)
  }

  useEffect(() => {
    // 전체 고민 가져오기
    const fetchConsult = async () => {
      try {
        let tempOtherConsult: consultType[] = await getConsults()
        setOtherConsult(tempOtherConsult)
      } catch (err) {
        console.log(err)
      }
    }
    fetchConsult()
  }, [])


  return (
    <div className="py-5 px-3 min-h-[40%]">
      <p className="text-2xl hover:cursor-pointer" onClick={() => navigate('/consult/other')}>다른 사람들의 고민 보기<span className='text-sm'>(오늘 남은 횟수: 5)</span></p>
      <div className="sm:flex sm:justify-between mt-4 mb-2">
        {/* 카테고리들 */}
        <Select
          label='카테고리 선택'
          size='sm'
          onChange={handleCategory}
          className='w-[150px]'
        >
          {category.map((oneCategory: categoryType) => {
            return (
              <SelectItem key={oneCategory.categoryId}>
                {oneCategory.categoryName}
              </SelectItem>
            )})
          }
        </Select>
        <p
          className='underline underline-offset-4 hover:cursor-pointer hidden sm:block'
          onClick={() => navigate('/consult/other')}
        >더보러가기</p>
      </div>
      <div className='mt-2 flex overflow-x-auto'>
        {
          otherConsults.map((consult, idx) => (
            <div className="w-44 h-48 m-2 min-w-44" key={idx}>
              <OtherConsult consult={consult} />
            </div>
          ))
        }
      </div>
    </div>
  )
}


// 공유된 고민
function Shared() {
  const navigate = useNavigate()

  // 카테고리 받기
  let category = useSelector((state: RootState) => state.consultSlice.category)
  // 선택된 카테고리
  const [selectedCategory, setSelectedCategory] = useState<categoryType | null>(null)
  const handleCategory = (e: any) => {
    setSelectedCategory(e.target.value)
    console.log(selectedCategory)
  }

  return (
    <div className="px-3 min-h-[40%] mt-3">
      <p className="text-2xl hover:cursor-pointer" onClick={() => navigate('/consult/shared')}>공유된 고민 상담들 둘러보기</p>
      <div className="sm:flex sm:justify-between sm:items-center">
        <div className="md:flex md:items-center w-5/6 mt-4 mb-2">
          {/* 카테고리들 */}
          <Select
            label='카테고리 선택'
            size='sm'
            onChange={handleCategory}
            className='md:mr-5 max-w-[150px]'
          >
            {
              category.map((oneCategory: categoryType) => {
                return (
                  <SelectItem key={oneCategory.categoryId}>
                    {oneCategory.categoryName}
                  </SelectItem>
                )
              })
            }
          </Select>
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
