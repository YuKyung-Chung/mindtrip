import React, { useState } from 'react';
import Calendar from 'react-calendar';
import './ReactCalendar.css'
import moment from 'moment';
import goodimo from '../../assets/imoticon/goodimo.png'

function testcal() {
  const curDate = new Date(); // 현재 날짜
  const [value, onChange] = useState(curDate); // 클릭한 날짜 (초기값으로 현재 날짜 넣어줌)
  const activeDate = moment(value).format('YYYY-MM-DD');
  const monthOfActiveDate = moment(value).format('YYYY-MM');
  const [activeMonth, setActiveMonth] = useState(monthOfActiveDate);
  const getActiveMonth = (activeStartDate: moment.MomentInput) => {
  const newActiveMonth = moment(activeStartDate).format('YYYY-MM');
    setActiveMonth(newActiveMonth);
  };
  const dayList = [
    '2024-03-10',
    '2024-03-21',
    '2024-04-02',
    '2024-04-14',
    '2024-04-27',
  ];

  const addContent = ({ date }: any) => {
    // 해당 날짜(하루)에 추가할 컨텐츠의 배열
    const contents = [];

    // date(각 날짜)가  리스트의 날짜와 일치하면 해당 컨텐츠(이모티콘) 추가
    if (dayList.find((day) => day === moment(date).format('YYYY-MM-DD'))) {
      contents.push(
        <>
          {/* <div className="dot">ㅇ</div> */}
          <img
            src={goodimo}
            className="diaryImg"
            width="26"
            height="26"
            alt="today is..."
          />
        </>
      );
    }
    return <div>{contents}</div>; // 각 날짜마다 해당 요소가 들어감
  };

  return (
    <div>
      <Calendar
            locale="en"
            onChange={onChange}
            value={value}
            next2Label={null}
            prev2Label={null}
            formatDay={(locale, date) => moment(date).format('D')}
            tileContent={addContent}
            showNeighboringMonth={false}
            onActiveStartDateChange={({ activeStartDate }) =>
              getActiveMonth(activeStartDate)
            }
          />
          </div>
  );
}

export default testcal