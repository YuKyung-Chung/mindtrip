import { useState } from 'react'
import { Button, ButtonGroup, Input } from "@nextui-org/react";
import { useNavigate } from 'react-router-dom';
import SharedConsult from '../../components/Consult/SharedConsult';
import SearchIcon from '../../atoms/Icons/SearchIcon';

function ConsultShared() {
  const navigate = useNavigate()

  // 선택된 카테고리 제어용
  const [selectedIndex, setSelectedIndex] = useState<number>(0)
  const changeIndex = (num: number) => () => {
    setSelectedIndex(num);
  }

  const categories: string[] = ['전체', '연애/결혼', '진로/취업', '기타']

  return (
    <div className="w-full lg:w-3/4 mx-auto h-screen pt-5">
      <div className="py-5 px-3 min-h-[40%]">
        <p className="text-2xl hover:cursor-pointer" onClick={() => navigate('/consult/shared')}>공유된 고민 상담들 둘러보기</p>
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
        <div className='mt-2'>
          <div className='w-full p-2 h-40'>
            <SharedConsult/>
          </div>
          <div className='w-full p-2 h-40'>
            <SharedConsult/>
          </div>
          <div className='w-full p-2 h-40'>
            <SharedConsult/>
          </div>
          <div className='w-full p-2 h-40'>
            <SharedConsult/>
          </div>
          <div className='w-full p-2 h-40'>
            <SharedConsult/>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ConsultShared