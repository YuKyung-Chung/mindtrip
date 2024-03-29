import React, { useState, useRef } from "react";
import moment from "moment";
import Calendar from "react-calendar";
import "./MyPostit.css";
import { px } from "framer-motion";

function MyPostit() {
  const [selectedDate, setSelectedDate] = useState(moment()); // 선택된 날짜 상태
  const [memos] = useState<{ [date: string]: string }>({
    "2024-03-15": "오늘은 술을 마셔야겠다.",
    "2024-03-16": "오늘도 술을 마셔야겠다.",
    "2024-03-17": "싸피를 때려 쳐야겠다.",
    "2024-03-18": "인생이 힘들다.",
    // 임시 메모 데이터
  });
  const [showCalendar, setShowCalendar] = useState(false);
  const calendarStyle = {
    width: '20px',
    height: '20px',
  }; // 달력 표시 여부 상태


  // 달력 토글 함수
  const toggleCalendar = () => {
    setShowCalendar(!showCalendar);
  };

  // 선택된 날짜 변경 함수
  const onChangeDate = (date: Date | Date[]) => {
    if (!Array.isArray(date)) {
      setSelectedDate(moment(date));
      toggleCalendar(); // 날짜를 선택하면 달력 숨기기
    }
  };

  return (
    <div className="my-postit-container">
      {/* <div style={{ width: '30px', height: '30px' }}> */}
        <h1 className="postit-date" onClick={toggleCalendar}>
          {selectedDate.format("YYYY년 MM월 DD일")}
          {showCalendar&&<Calendar />}
        </h1>
      {/* </div>        */}
      <div className="mt-6">그날의 질문</div>
      {showCalendar && (
        <div className="calendar-wrapper">
        </div>
      )}
      <div className="relative overflow-hidden w-60 h-80 rounded-3xl cursor-pointer text-2xl font-bold bg-purple-400">
        <div className="z-10 absolute w-full h-full peer"></div>
        <div className="absolute peer-hover:-top-20 peer-hover:-left-16 peer-hover:w-[140%] peer-hover:h-[140%] -top-32 -left-16 w-32 h-44 rounded-full bg-purple-300 transition-all duration-500"></div>
        <div className="absolute flex text-xl text-center items-end justify-end peer-hover:right-0 peer-hover:rounded-b-none peer-hover:bottom-0 peer-hover:items-center peer-hover:justify-center peer-hover:w-full peer-hover:h-full -bottom-32 -right-16 w-36 h-44 rounded-full bg-purple-300 transition-all duration-500">
        <div className="">
          {memos[selectedDate.format("YYYY-MM-DD")] || ""}
        </div>
        </div>
        <div className="w-full h-full items-center justify-center flex uppercase">
          
        </div>
      </div>
    </div>
  );
}

export default MyPostit;
