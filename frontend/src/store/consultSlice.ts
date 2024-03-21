import { createSlice } from '@reduxjs/toolkit'

// 상담용 변수들인데 안써도 될듯?


// 카테고리들
// 1. 타입 정의
type ConsultCategoryType = {
  categoryId :number,
  categoryName :string
}

// 2. 초기 상태 정의
const initialConsultCategoryState :ConsultCategoryType[] | null = null

// 3. 슬라이스 정의
let consultCategory = createSlice({
  name: 'consultCategory',
  initialState: initialConsultCategoryState,
  reducers: {
    
  }
})

// 4. 함수들 내보내기
export const {  } = consultCategory.actions;


export {consultCategory}