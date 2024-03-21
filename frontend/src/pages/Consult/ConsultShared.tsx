import { useState } from 'react'
import { Select, SelectItem, Input } from "@nextui-org/react";
import { useNavigate } from 'react-router-dom';
import SharedConsult from '../../components/Consult/SharedConsult';
import SearchIcon from '../../atoms/Icons/SearchIcon';
import { categoryType } from '../../types/DataTypes';
import { useSelector } from "react-redux";
import { RootState } from './../../store/store'

function ConsultShared() {
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
    <div className="w-full lg:w-3/4 mx-auto h-screen pt-5">
      <div className="py-5 px-3 min-h-[40%]">
        <p className="text-2xl hover:cursor-pointer" onClick={() => navigate('/consult/shared')}>공유된 고민 상담들 둘러보기</p>
        <div className="md:flex md:items-center w-5/6 mt-4 mb-2">
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
        <div className='mt-2'>
          <div className='w-full p-2 h-40'>
            <SharedConsult />
          </div>
          <div className='w-full p-2 h-40'>
            <SharedConsult />
          </div>
          <div className='w-full p-2 h-40'>
            <SharedConsult />
          </div>
          <div className='w-full p-2 h-40'>
            <SharedConsult />
          </div>
          <div className='w-full p-2 h-40'>
            <SharedConsult />
          </div>
        </div>
      </div>
    </div>
  )
}

export default ConsultShared