// import { useState } from 'react';
// import Calendar from 'react-calendar';
// import './ReactCalendar.css'
// import moment from 'moment';
// import goodimo from '../../assets/imoticon/goodimo.png'
// import sosoimo from '../../assets/imoticon/sosoimo.png'
// import badimo from '../../assets/imoticon/badimo.png'

// function TestCal() {
//   const curDate = new Date(); // 현재 날짜
//   const [value, onChange] = useState(curDate); // 클릭한 날짜 (초기값으로 현재 날짜 넣어줌)
//   const activeDate = moment(value).format('YYYY-MM-DD');
//   const monthOfActiveDate = moment(value).format('YYYY-MM');
//   const [activeMonth, setActiveMonth] = useState(monthOfActiveDate);
//   const getActiveMonth = (activeStartDate: moment.MomentInput) => {
//   const newActiveMonth = moment(activeStartDate).format('YYYY-MM');
//     setActiveMonth(newActiveMonth);
//   };
//   const dayList = [
//     '2024-03-10',
//     '2024-03-21',
//     '2024-04-02',
//     '2024-04-14',
//     '2024-04-27',
//   ];

//   const addContent = ({ date }: any) => {
//     // 해당 날짜(하루)에 추가할 컨텐츠의 배열
//     const contents = [];

//     // date(각 날짜)가  리스트의 날짜와 일치하면 해당 컨텐츠(이모티콘) 추가
//     if (dayList.find((day) => day === moment(date).format('YYYY-MM-DD'))) {
//       contents.push(
//         <>
//           <img
//             src={goodimo}
//             className="diaryImg"
//             width="26"
//             height="26"
//             alt="today is..."
//           />
//         </>
//       );
//     }
//     return <div>{contents}</div>; // 각 날짜마다 해당 요소가 들어감
//   };

//   return (
//     <div>
//       <Calendar
//             locale="en"
//             onChange={onChange}
//             value={value}
//             next2Label={null}
//             prev2Label={null}
//             formatDay={(locale, date) => moment(date).format('D')}
//             formatShortWeekday={(locale, date) => moment(date).format('dd')}
//             tileContent={addContent}
//             showNeighboringMonth={false}
//             onActiveStartDateChange={({ activeStartDate }) =>
//               getActiveMonth(activeStartDate)
//             }
//           />
//           </div>
//   );
// }

// export default TestCal

import { useState, useEffect } from 'react';
import axios from 'axios'; // axios 임포트
import Calendar from 'react-calendar';
import './ReactCalendar.css';
import moment from 'moment';
import goodimo from '../../assets/imoticon/goodimo.png';
import sosoimo from '../../assets/imoticon/sosoimo.png';
import badimo from '../../assets/imoticon/badimo.png';


// API 나오면 쓸것
// function TestCal() {
//   const curDate = new Date();
//   const [value, onChange] = useState(curDate);
//   const [dayData, setDayData] = useState({});

//   // useEffect 안에서 axios를 이용해 API 호출
//   useEffect(() => {
//     const fetchData = async () => {
//       try {
//         const response = await axios.get('API 엔드포인트');
//         setDayData(response.data); // API 응답 데이터를 상태로 설정
//       } catch (error) {
//         console.error('Error fetching data: ', error);
//       }
//     };

//     fetchData(); // 컴포넌트가 마운트될 때 한 번만 데이터를 가져옴
//   }, []);


function TestCal() {
  const curDate = new Date();
  const [value, onChange] = useState(curDate);
  const [dayData, setDayData] = useState({});
  const [missionInfo, setMissionInfo] = useState({});
  const [selectedMissions, setSelectedMissions] = useState([]);

  useEffect(() => {
    // 임시 데이터 설정
    const tempData = {
      '2024-03-10': 3,
      '2024-03-11': 3,
      '2024-03-12': 3,
      '2024-03-13': 3,
      '2024-03-14': 3,
      '2024-03-15': 3,
      '2024-03-16': 3,
      '2024-03-17': 3,
      '2024-03-18': 3,
      '2024-03-19': 3,
      '2024-03-20': 3,
    };

    setDayData(tempData); // 임시 데이터를 상태로 설정

    // 임시 미션 데이터 설정
    const tempMissionData = {
      '2024-03-10': {
        count: 2,
        missions: [
          { missionname: '미션 1', isFinish: true },
          { missionname: '미션 2', isFinish: true },
          { missionname: '미션 3', isFinish: false },
          { missionname: '미션 2', isFinish: true },
          { missionname: '미션 3', isFinish: false },
          { missionname: '미션 2', isFinish: true },
          { missionname: '미션 3', isFinish: false },
          { missionname: '미션 2', isFinish: true },
          { missionname: '미션 3', isFinish: false },
        ],
      },
      '2024-03-11': {
        count: 2,
        missions: [
          { missionname: '미션 4', isFinish: true },
          { missionname: '미션 5', isFinish: true },
          { missionname: '미션 6', isFinish: false },
        ],
      },
      // 이하 생략
    };

    setMissionInfo(tempMissionData); // 임시 미션 데이터를 상태로 설정
  }, []);

  const addContent = ({ date }) => {
    const contents = [];
    const formattedDate = moment(date).format('YYYY-MM-DD');
    const dayValue = dayData[formattedDate];

    if (dayValue === 3) {
      contents.push(
        <img
          key={formattedDate}
          src={goodimo}
          className="diaryImg"
          width="26"
          height="26"
          alt="good day"
        />
      );
    } else if (dayValue === 2 || dayValue === 1) {
      contents.push(
        <img
          key={formattedDate}
          src={sosoimo}
          className="diaryImg"
          width="26"
          height="26"
          alt="so-so day"
        />
      );
    } else {
      contents.push(
        <img
          key={formattedDate}
          src={badimo}
          className="diaryImg"
          width="26"
          height="26"
          alt="bad day"
        />
      );
    }
    
    return <div>{contents}</div>;
  };

  const handleDayClick = (date) => {
    const formattedDate = moment(date).format('YYYY-MM-DD');
    const missions = missionInfo[formattedDate]?.missions || [];

    if (missions.length === 0) {
      setSelectedMissions(['해당 날짜에 미션이 없습니다.']);
    } else {
      const missionStatus = missions.map((mission) => {
        return `${mission.missionname}: ${mission.isFinish ? '성공' : '실패'}`;
      });

      setSelectedMissions(missionStatus);
    }
  };

  return (
    <>
      <Calendar
        locale="en"
        onChange={onChange}
        value={value}
        next2Label={null}
        prev2Label={null}
        formatDay={(locale, date) => moment(date).format('D')}
        formatShortWeekday={(locale, date) => moment(date).format('dd')}
        tileContent={addContent}
        showNeighboringMonth={false}
        onClickDay={handleDayClick} // 날짜를 클릭했을 때 이벤트 핸들러 추가
      />
      {selectedMissions.length > 0 && (
        <div className="mt-4 text-center bg-white w-4/5 p-4 rounded-lg border border-gray-300 mx-auto">
          <p>선택한 날짜의 미션 정보:</p>
          {selectedMissions.map((mission, index) => (
            <p key={index}>{mission}</p>
          ))}
        </div>
      )}
    </>
  );
}

export default TestCal;
