// import React, { useState } from 'react';
import MyProgress from "../../../components/Progress/Progress"
// import Calendar from "../../../components/Calendar/Calendar"
import MyCalendar from '../../../components/Calendar/ReactCalendar';

function MypageProgress() {
  
  return (
    <div className="bg-[#f4c2c2] flex justify-center flex-col align-items items-center min-h-screen relative">
      <div className="bg-white rounded-lg p-4 w-4/5 flex justify-center ">
        <MyProgress />
      </div>
      <span>월간보고서</span>
      <div>
        <MyCalendar />
      </div>
    </div>
  );
}

export default MypageProgress;
