import React, { useState } from 'react';
import Calendar from "../../../components/Calendar/Calendar";

interface Memo {
  date: Date;
  content: string;
}

interface Props {
  memos: Memo[]; // Memo 배열을 props로 받음
}

const MypageHistory: React.FC<Props> = ({ memos }) => {
  const [currentDateIndex, setCurrentDateIndex] = useState<number>(0); // 현재 날짜 인덱스 상태

  // 이전 날짜로 이동
  const moveToPreviousDate = () => {
    if (memos && memos.length > 0 && currentDateIndex > 0) {
      setCurrentDateIndex(currentDateIndex - 1);
    }
  };

  // 다음 날짜로 이동
  const moveToNextDate = () => {
    if (memos && memos.length > 0 && currentDateIndex < memos.length - 1) {
      setCurrentDateIndex(currentDateIndex + 1);
    }
  };

  // 선택된 날짜에 해당하는 메모를 가져오는 함수
  const getMemoForSelectedDate = () => {
    if (memos && memos.length > 0) {
      const memo = memos[currentDateIndex];
      return memo ? memo.content : null;
    }
    return null;
  };

  return (
    <div className="bg-[#f4c2c2] flex justify-end flex-col min-h-screen relative">
      <div className="absolute top-0 right-0 m-4">
        <Calendar
          selectedDate={memos && memos.length > 0 ? memos[currentDateIndex]?.date : new Date()} // 옵셔널 체이닝 사용
          setSelectedDate={(date) => {
            // 선택된 날짜에 해당하는 메모의 인덱스를 찾아서 설정
            const index = memos.findIndex((memo) => memo.date === date);
            if (index !== -1) {
              setCurrentDateIndex(index);
            }
          }}
        />
      </div>
      <div className="text-center">
        <button onClick={moveToPreviousDate}>이전 날짜</button>
        <span>{getMemoForSelectedDate() || '메모가 없습니다.'}</span>
        <button onClick={moveToNextDate}>다음 날짜</button>
      </div>
    </div>
  );
};

export default MypageHistory;
