import { Button, Input, Textarea, Select, SelectItem } from "@nextui-org/react";
import { useState } from 'react'
import { uploadConsult } from "../../api/consults";
import { categoryType } from "../../types/DataTypes";

type propsType = {
  onClose: () => void
  category: categoryType[] | null
}

function CreateNewConsult({ onClose, category }: propsType) {
  // 제목, 내용, 카테고리
  const [title, setTitle] = useState<string>('')
  const [content, setContent] = useState<string>('')
  
  // 선택된 카테고리
  const [selectedCategory, setSelectedCategory] = useState<categoryType|null>(null)
  const handleCategory = (e :any) => {
   setSelectedCategory(e.target.value)
  }

  // 유효성 검사용 변수들
  const [titleErrorMessage, setTitleErrorMessage] = useState<string>('')
  const [contentErrorMessage, setContentErrorMessage] = useState<string>('')
  // 제출하기 
  const submit = function () {
    // 제출 전 유효성 검사
    if (title.length < 1) {
      setTitleErrorMessage('제목을 입력해주세요')
    } else if (title.length > 20) {
      setTitleErrorMessage('제목은 최대 20자입니다.')
    } else if (content.length < 1) {
      setTitleErrorMessage('')
      setContentErrorMessage('내용을 입력해주세요')
    } else if (content.length > 100) {
      setContentErrorMessage('내용은 최대 100자입니다.')
    } else {
      setContentErrorMessage('')
      
      //여기에 axios 통신
      let temp :number|string|categoryType = selectedCategory ? selectedCategory : 0
      
      uploadConsult(title, content, temp)
      onClose()
    }
  }
  return (
    <div className="flex-col h-[50vh] pt-3">
      {/* 고민 제목 */}
      <Input
        isInvalid={titleErrorMessage == '' ? false : true}
        errorMessage={titleErrorMessage}
        label='고민 제목'
        variant="bordered"
        labelPlacement="outside"
        placeholder="고민 제목을 입력해주세요"
        value={title}
        onValueChange={setTitle}
      />
      {/* 고민 내용 */}
      <Textarea
        isInvalid={contentErrorMessage == '' ? false : true}
        errorMessage={contentErrorMessage}
        label='고민 내용'
        variant="bordered"
        labelPlacement="outside"
        placeholder="고민 내용을 입력해주세요"
        value={content}
        onValueChange={setContent}
        className="my-5"
      />
      {/* 카테고리들 */}
      {
            category != null ? (
              <Select
                label='카테고리 선택'
                size='sm'
                onChange={handleCategory}
                className='w-[170px] mr-7'
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
            ) : null
          }
      <Button 
        onPress={submit} 
        className="mt-7 ml-[70%] w-[30%]"
      >공유하기</Button>
    </div>
  )
}
export default CreateNewConsult