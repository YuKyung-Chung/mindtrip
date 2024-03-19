import React, { useState, useRef } from 'react';
import moment from 'moment';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './MyPostit.css';

function MyPostit() {
  const [selectedDate, setSelectedDate] = useState(moment()); // 선택된 날짜 상태
  const [memos, setMemos] = useState<{ [date: string]: string }>({
<<<<<<< HEAD
    '2024-03-15': '오늘은 술을 마셔야겠다.',
    '2024-03-16': '오늘도 술을 마셔야겠다.',
    '2024-03-17': '싸피를 때려 쳐야겠다.',
=======
    '2024-03-15': '오늘은 쇼핑을 가야겠다.',
    '2024-03-16': '운동을 꾸준히 해야겠다.',
    '2024-03-17': '친구와 약속이 있으니 준비해야겠다.',
>>>>>>> develop
    // 임시 메모 데이터
  });
  const [showCalendar, setShowCalendar] = useState(false); // 달력 표시 여부 상태
  const dragStartRef = useRef<number>(0);

  // 드래그 시작
  const handleDragStart = (e: React.DragEvent<HTMLDivElement>) => {
    dragStartRef.current = e.clientX;
  };

  // 드래그 종료
  const handleDragEnd = (e: React.DragEvent<HTMLDivElement>) => {
    const dragEnd = e.clientX;
    const distance = dragEnd - dragStartRef.current;
    const threshold = 50; // 드래그 임계값
    if (Math.abs(distance) >= threshold) {
      const days = distance > 0 ? 1 : -1; // 오른쪽으로 드래그하면 1일 뒤로, 왼쪽으로 드래그하면 1일 전으로 변경
      setSelectedDate(selectedDate.clone().add(days, 'days'));
    }
  };

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
      <h1 className="postit-date" onClick={toggleCalendar}>{selectedDate.format('YYYY년 MM월 DD일')}</h1>
      {showCalendar && (
        <div className="calendar-wrapper">
          <Calendar
            onChange={onChangeDate}
            value={new Date(selectedDate.format('YYYY-MM-DD'))}
          />
        </div>
      )}
      <div
        className="postit-content"
        draggable={true}
        onDragStart={handleDragStart}
        onDragEnd={handleDragEnd}
      >
        <div className="postit-text">{memos[selectedDate.format('YYYY-MM-DD')] || ''}</div>
      </div>
    </div>
  );
}

export default MyPostit;
