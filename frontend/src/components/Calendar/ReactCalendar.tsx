import { useState } from "react";
import Calendar from "react-calendar";
import "./ReactCalendar.css";
import moment from "moment";
import Missionbox from "../missonbox/MissionBox";

function MyCalendar() {
  const [value, onChange] = useState(new Date());
  const [mark, setMark] = useState([
    "2024-03-15",
    "2024-03-20", // 임시데이터 넣은것
  ]);
  const [missions, setMissions] = useState<string[]>([]);

  const handleCalendarChange = (date: Date) => {
    onChange(date); // 선택된 날짜로 상태를 업데이트

    // 임시미션 리스트
    const dateStr = moment(date).format("YYYY-MM-DD");
    const temporaryMissions: Record<string, string[]> = {
      "2024-03-15": ["양치하기", "술먹기"],
      "2024-03-20": ["회식하기", "야구보기"],
    };

    if (temporaryMissions[dateStr]) {
      setMissions(temporaryMissions[dateStr]);
    } else {
      setMissions([]);
    }
  };

  return (
    <div>
      <Calendar
        onChange={handleCalendarChange}
        formatDay={(locale, date) => moment(date).format("D")}
        value={value}
        next2Label={null}
        prev2Label={null}
        className="mx-auto text-sm border-b"
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
        {moment(value).format("YYYY년 MM월 DD일")}의 미션
      </div>
      <div className="mt-4">
        {missions.map((mission, index) => (
          <li key={index}>
            {index} {mission}
          </li>
        ))}
      </div>
    </div>
  );
}

export default MyCalendar;
