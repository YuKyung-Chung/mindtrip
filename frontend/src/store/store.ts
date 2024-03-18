import { configureStore, createSlice, PayloadAction } from '@reduxjs/toolkit'

// 채팅 관리용 변수들
// 1. 타입 정의
type ChatState = {
  isList: boolean
  selectedId: number | null
}

// 2. 초기 상태 정의
const initialChatState :ChatState = {
  isList: true,
  selectedId: null
}

// 3. 슬라이스 정의
let chatSlice = createSlice({
  name: 'chat',
  initialState: initialChatState,
  reducers: {
    temp(state) {
      state.isList = !state.isList
    }
  }
})

// 4. 함수들 내보내기
export const { temp } = chatSlice.actions;

// 스토어 생성
const store = configureStore({
  reducer: {
    chat: chatSlice.reducer,
  },
})

// 스토어의 타입을 내보내기 위한 유틸리티 타입
export type AppDispatch = typeof store.dispatch;
export type AppStore = typeof store;

// 여기서 RootState 타입을 정의하고 내보냅니다.
export type RootState = ReturnType<typeof store.getState>;



// 스토어 내보내기
export default store;