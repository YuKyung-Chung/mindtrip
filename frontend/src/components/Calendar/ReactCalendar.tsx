import React, { useState } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import moment from 'moment';
import { useQuery } from 'react-query';
import axios from 'axios';

function MyCalendar() {
  const [value, onChange] = useState(new Date());
  const [mark, setMark] = useState([]);
  const [loading, setLoading] = useState(true); // 로딩 상태 추가

  const { data } = useQuery(
    ["logDate", month],
    async () => {
      const result = await axios.get(
        `/api/healthLogs?health_log_type=DIET`
      );
      return result.data;
    },
    {
      onSuccess: (data: any) => {
        setMark(data);
        setLoading(false); // 데이터를 가져왔을 때 로딩 상태 업데이트
      },
    }
  );

  if (loading) {
    return <div>Loading...</div>; // 데이터를 가져오는 동안 로딩 상태 표시
  }

  return (
    <div>
      <Calendar
        onChange={onChange}
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
