import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Missionbox from '../../components/missionbox';

function Fixmission() {
  // 미션 데이터를 저장할 상태
  const [missions, setMissions] = useState([]);

  // useEffect를 사용하여 API 호출
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('https://mindtrip.site/api/missions/v1'); // API 엔드포인트 URL을 사용
        // setMissions(response.data.result.categoryMissionResList); // 받아온 데이터 중 미션 목록을 상태로 설정
        console.log(response)
      } catch (error) {
        console.log('Error fetching missions:', error);
      }
    };

    fetchData(); // fetchData 함수 호출
  }, []); // 빈 배열을 전달하여 컴포넌트가 처음 렌더링될 때만 호출하도록 설정

  return (
    <div className="bg-white p-8">
      {/* 미션 목록 출력 */}
      <div className="mt-8 grid grid-cols-2 gap-8">
        <div className='allmission' style={{ overflowY: "auto", maxHeight: "400px" }}>
          {/* API에서 받아온 미션 목록을 반복하여 출력 */}
          {missions.map((categoryMission, index) => (
            <div key={index}>
              <h2>{categoryMission.category}</h2>
              {/* 카테고리별로 미션 출력 */}
              {categoryMission.missionBaseResList.map((mission, index) => (
                <Missionbox key={index} mission={mission} />
              ))}
            </div>
          ))}
        </div>
        {/* 셀프테스트 미션 영역 */}
        <div className="bg-[#FFF7ED] p-4 rounded-lg">
          <h2 className="text-xl font-semibold mb-4">셀프테스트 미션</h2>
          <div className="space-y-4">
            {/* 하드코딩된 예시 데이터 */}
            <div className="flex justify-between items-center">
              <p>일어나서 좋아하는 노래 듣기</p>
            </div>
            <div className="flex justify-between items-center">
              <p>산책하기</p>
            </div>
            <div className="flex justify-between items-center">
              <p>반신욕하기</p>
            </div>
          </div>
        </div>
      </div>
      <div className="mt-8 flex justify-center"></div>
    </div>
  );
}

export default Fixmission;
