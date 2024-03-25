import { createSlice } from '@reduxjs/toolkit'
import { memberType } from '../types/DataTypes';
// 회원용 변수들

// 1. 타입정의 -> 타입 파일에

// 2. 초기 상태 정의
const initialMemberState :memberType = {
  nickname: null,
  socialId: null,
  villageId: null,
  villageName: 'grape',
  level: null,
  missionCount: null,
  accessToken: null
}

// 3. 슬라이스 정의
let memberSlice = createSlice({
  name: 'member',
  initialState: initialMemberState,
  reducers: {
    // 여기에 함수 정의
  }
})

// 4. 함수들 내보내기
export const {} = memberSlice.actions;


export default memberSlice