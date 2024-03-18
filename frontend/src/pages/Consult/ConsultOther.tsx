import { useState } from 'react'
import { Button, ButtonGroup } from "@nextui-org/react";
import { useNavigate } from 'react-router-dom';
import OtherConsult from '../../components/Consult/OtherConsult';

function ConsultOther() {
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
    </div>
  )
}

export default ConsultOther