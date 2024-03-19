import React, { useState } from 'react';
import MyPostit from "../../../components/MyPostit/MyPostit";

interface Memo {
  date: Date;
  content: string;
}

interface Props {
  memos: Memo[]; // Memo 배열을 props로 받음
}

function MypageHistory() {
  return (
    <div className="bg-[#f4c2c2] flex justify-end flex-col min-h-screen relative">
        <h1 className="text-2xl font-bold mb-4">내기록</h1>
      <div className="flex justify-center items-center min-h-screen">
      <div>
        <MyPostit />
      </div>
    </div>
    </div>
  );
};

export default MypageHistory;
