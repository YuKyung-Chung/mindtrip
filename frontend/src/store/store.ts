import { configureStore } from '@reduxjs/toolkit'
import chatSlice from './chatSlice';
import {consultCategory} from './consultSlice';

// 스토어 생성
const store = configureStore({
  reducer: {
    chat: chatSlice.reducer,
    consultCategory: consultCategory.reducer
  },
})

// 스토어의 타입을 내보내기 위한 유틸리티 타입
export type AppDispatch = typeof store.dispatch;
export type AppStore = typeof store;

// 여기서 RootState 타입을 정의, export
export type RootState = ReturnType<typeof store.getState>;


// 스토어 내보내기
export default store;