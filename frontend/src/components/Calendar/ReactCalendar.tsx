import { useState } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import moment from 'moment';

function MyCalendar() {
  const [value, setValue] = useState(new Date());
  const [mark, setMark] = useState([
    "2024-03-15", 
    "2024-03-20", // 임시데이터 넣은것
  ]);

  const handleCalendarChange = (date) => {
    setValue(date); // 선택된 날짜로 상태를 업데이트
  };

  return (
    <div>
      <Calendar
        onChange={handleCalendarChange}
        formatDay={(locale, date) => moment(date).format("DD")}
        value={value}
        className="mx-auto w-full text-sm border-b"
        tileContent={({ date, view }) => {
          if (mark.find((x) => x === moment(date).format("YYYY-MM-DD"))) {
            return (
              <div className="react-calendar__tile--content">
                <div className="dot"></div>
              </div>
            );
          } else {
            return null;
          }
        }}
      />
      <div className="text-gray-500 mt-4">
        {moment(value).format("YYYY년 MM월 DD일")} 
      </div>
    </div>
  );
}

export default MyCalendar;
