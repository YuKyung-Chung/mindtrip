import { createSlice, PayloadAction } from '@reduxjs/toolkit'
import { categoryType } from '../types/DataTypes'
// 상담용 변수들

type ConsultType = {
  category :categoryType[]
}

// 2. 초기 상태 정의
const initialConsultState :ConsultType|null = {category : []}

// 3. 슬라이스 정의
let consultSlice = createSlice({
  name: 'consult',
  initialState: initialConsultState,
  reducers: {
    // 처음에 카테고리 채워넣기
    getConsultCategory(state, action: PayloadAction<categoryType[]>) {
      state.category = action.payload
    }
  }
})

// 4. 함수들 내보내기
export const { getConsultCategory } = consultSlice.actions;


export {consultSlice}