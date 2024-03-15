import { useState } from 'react'
import { Button, ButtonGroup, Input } from "@nextui-org/react";
import OtherConsult from '../../components/Consult/OtherConsult';
import SharedConsult from '../../components/Consult/SharedConsult';
import BackButton from '../../atoms/buttons/backbtn';

// 고민상담소 첫 페이지

function Consult() {
  return (
    <div className="w-full md:w-4/5 mx-auto h-screen pt-5">
      <Others />
      <Shared />
      <BackButton />
    </div>
  )
}

export default Consult

// 다른 사람들의 고민
function Others() {
  // 선택된 카테고리 제어용
  const [selectedIndex, setSelectedIndex] = useState<number>(0)
  const changeIndex = (num: number) => () => {
    setSelectedIndex(num);
  }

  const categories: string[] = ['전체', '연애/결혼', '진로/취업', '기타']

  return (
    <div className="py-5 px-3 min-h-[40%]">
      <p className="text-2xl">다른 사람들의 고민 보기</p>
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
        <p className='underline underline-offset-4 hover:cursor-pointer mt-2 sm:mt-0'>더보러가기</p>
      </div>
      <div className='mt-2 flex overflow-x-auto'>
        <div  className="w-44 h-48 m-2 min-w-44">
          <OtherConsult />
        </div>
        <div  className="w-44 h-48 m-2 min-w-44">
          <OtherConsult />
        </div>
        <div  className="w-44 h-48 m-2 min-w-44">
          <OtherConsult />
        </div>
        <div  className="w-44 h-48 m-2 min-w-44">
          <OtherConsult />
        </div>
        <div  className="w-44 h-48 m-2 min-w-44">
          <OtherConsult />
        </div>
      </div>
    </div>
  )
}


// 공유된 고민
function Shared() {
  // 선택된 카테고리 제어용
  const [selectedIndex, setSelectedIndex] = useState<number>(0)
  const changeIndex = (num: number) => () => {
    setSelectedIndex(num);
  }

  const categories: string[] = ['전체', '연애/결혼', '진로/취업', '기타']

  return (
    <div className="px-3 min-h-[40%] mt-10">
      <p className="text-2xl">공유된 고민 상담들 둘러보기</p>
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
        <p className='underline underline-offset-4 hover:cursor-pointer'>더보러가기</p>
      </div>
      <div className='mt-2 flex overflow-x-auto'>
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
        <div className="w-44 m-2 min-w-44">
          <SharedConsult />
        </div>

      </div>
    </div>
  )
}



// 그냥 검색 아이콘
function SearchIcon() {
  return (
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" className="w-5 h-5">
      <path fillRule="evenodd" d="M9 3.5a5.5 5.5 0 1 0 0 11 5.5 5.5 0 0 0 0-11ZM2 9a7 7 0 1 1 12.452 4.391l3.328 3.329a.75.75 0 1 1-1.06 1.06l-3.329-3.328A7 7 0 0 1 2 9Z" clipRule="evenodd" />
    </svg>
  )
}