import { useState, useEffect } from "react";
import axios from "axios";
import { Link } from 'react-router-dom';
import Successbtn from '../../atoms/buttons/successbtn';
import Homebtn from "../../atoms/buttons/homebtn";


// 미션 타입 정의
type Mission =  {
  missionId: number;
  name: string;
  isFinish: boolean;
}

function Mission() {
  // 미션 데이터 상태
  const [missions, setMissions] = useState<Mission[]>([]); // 미션 타입을 명시

  // 페이지가 로드될 때 미션 데이터를 가져오는 useEffect 훅
  useEffect(() => {
    const fetchMissions = async () => {
      try {
        // 미션 데이터를 가져오는 GET 요청
        const response = await axios.get("https://mindtrip.site/api/missions/v1/mytable", {
          headers: {
            "x-member-id": "1"
          }
        });
        console.log(response.data)
        // 가져온 미션 데이터를 상태에 설정
        setMissions(response.data.result);
      } catch (error) {
        console.error("Error fetching missions:", error);
      }
    };

    // 함수 실행
    fetchMissions();
  }, []);

  // 미션 성공 처리 함수
  const handleMissionSuccess = async (missionId: number) => {
    try {
      // 해당 미션의 isFinish를 true로 변경하는 POST 요청
      const response = await axios.post(
        `https://mindtrip.site/api/missions/v1/mytable/${missionId}`,
        {},
        {
          headers: {
            "x-member-id": "1"
          }
        }
      );
      console.log("Success:", response.data);
      
      // 상태 업데이트: 해당 미션의 isFinish를 true로 변경
      setMissions(prevMissions => 
        prevMissions.map(mission => 
          mission.missionId === missionId ? {...mission, isFinish: true} : mission
        )
      );
    } catch (error) {
      console.error("Error updating mission:", error);
    }
  };

  return (
    <div className="bg-[#fff7e0] px-2 py-8 h-screen">
      <Homebtn />
      <div className="flex flex-col justify-center items-center mb-6">
        <h1 className="text-5xl font-bold mt-20">오늘의 미션</h1>
      </div>
      <div className="flex justify-end text-sm mb-4 w-4/5 mx-auto">
        <span>달성률: {missions.filter(mission => mission.isFinish).length}/{missions.length}</span>
      </div>
      <div className="space-y-4">
        {/* 미션 데이터를 반복하여 출력 */}
        {missions.map((mission) => (
          <div key={mission.missionId} className="flex justify-between items-center p-2 bg-white rounded-lg shadow w-4/5 mx-auto">
            {/* 미션 이름 출력 */}
            <span className="text-lg">{mission.name}</span>
            {/* 성공 버튼 */}
            <Successbtn isFinish={mission.isFinish} missionId={mission.missionId} onClick={() => handleMissionSuccess(mission.missionId)} />
          </div>
        ))}
      </div>
      <div className="mt-8 p-2 rounded-lg text-center w-4/5 mx-auto">
        <p className="mb-4">오늘의 미션이 맘에 들지 않았다면,</p>
        {/* 수정하러 가는 링크 */}
        <Link to="/fixmission" className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700 mt-4">
          수정하러가기
        </Link>
      </div>
    </div>
  );
}

export default Mission;
