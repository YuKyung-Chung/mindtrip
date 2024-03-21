import { configureStore, createSlice, PayloadAction } from '@reduxjs/toolkit'

// 채팅 관리용 변수들
// 1. 타입 정의
type ChatState = {
  isOpen: boolean
  isList: boolean
  selectedId: number | null
}

// 2. 초기 상태 정의
const initialChatState :ChatState = {
  isOpen: false,
  isList: true,
  selectedId: null
}

// 3. 슬라이스 정의
let chatSlice = createSlice({
  name: 'chat',
  initialState: initialChatState,
  reducers: {
    // 채팅 여닫기 제어
    toggleOpen(state) {
      state.isOpen = !state.isOpen
    },
    // 보이는게 리스트인지(true) / 하나의 채팅방인지(false)
    changeList(state, action: PayloadAction<boolean>) {
      state.isList = action.payload
    }
  }
})

// 4. 함수들 내보내기
export const { toggleOpen, changeList } = chatSlice.actions;

// 스토어 생성
const store = configureStore({
  reducer: {
    chat: chatSlice.reducer,
  },
})

// 스토어의 타입을 내보내기 위한 유틸리티 타입
export type AppDispatch = typeof store.dispatch;
export type AppStore = typeof store;

// 여기서 RootState 타입을 정의, export
export type RootState = ReturnType<typeof store.getState>;


// 스토어 내보내기
export default store;