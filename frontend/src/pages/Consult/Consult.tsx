import { useState } from 'react'
import { Button, ButtonGroup, Input} from "@nextui-org/react";
import OtherConsult from '../../components/Consult/OtherConsult';
import SharedConsult from '../../components/Consult/SharedConsult';
import BackButton from '../../atoms/buttons/backbtn';
import { useNavigate } from 'react-router-dom';
import SearchIcon from '../../atoms/Icons/SearchIcon';
import ChatModalBtn from '../../components/Consult/ChatModalBtn';
// 고민상담소 첫 페이지

function Consult() {
  return (
    <div className='relative'>
      <div className="w-full md:w-4/5 mx-auto h-screen pt-5">
        <Others />
        <Shared />
        <div className='hidden sm:block'>
          <BackButton />
        </div>
      </div>
      <ChatModalBtn />
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
    <div className="px-3 min-h-[40%] mt-10">
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
