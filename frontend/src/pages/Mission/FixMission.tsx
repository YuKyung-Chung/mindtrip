import React, { useState, useEffect } from "react";
import axios from "axios";
import FixMissionBox from "../../components/missonbox/FixMissionBox";
import { Link } from "react-router-dom";
import { Button } from "@nextui-org/react";

type Mission = {
  missionId: number;
  name: string;
  category: string;
  isFinish: boolean;
};

type CategoryMission = {
  category: string;
  missionBaseResList: Mission[];
};

type missionData = CategoryMission[];

function Fixmission() {
  const [missions, setMissions] = useState<missionData>([]);
  const [todayMissions, setTodayMissions] = useState<Mission[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const allMissionsResponse = await axios.get(
          "https://mindtrip.site/api/missions/v1"
        );
        const allMissionsData = await allMissionsResponse.data.result.categoryMissionResList;
        setMissions(await allMissionsData);

        const todayMissionsResponse = await axios.get(
          "https://mindtrip.site/api/missions/v1/mytable",
          {
            headers: {
              "x-member-id": "1" 
            }
          }
        );
        console.log(todayMissionsResponse.data)
        const todayMissionIds = await todayMissionsResponse.data.result.map(
          (item: { missionId: number }) => item.missionId
        );

        const todayMissionsFiltered = await allMissionsData.reduce(
          (acc: Mission[], categoryMission: CategoryMission) => {
            const filteredMissions = categoryMission.missionBaseResList.filter(
              (mission: Mission) => todayMissionIds.includes(mission.missionId)
            );
            return [...acc, ...filteredMissions];
          },
          []
        );

        setTodayMissions(await todayMissionsFiltered);
      } catch (error) {
        console.log("Error fetching missions:", error);
      }
    };

    fetchData();
  }, []);

  useEffect(()=>{
    console.log(todayMissions, missions);
  }, [todayMissions, missions])
 
  const addMissionToToday = (mission: Mission) => {
    // 중복된 미션인지 확인
    if (todayMissions.find((item) => item.missionId === mission.missionId)) {
      console.log("이미 추가된 미션입니다.");
      return;
    }

    // 최대 3개까지만 추가 가능
    if (todayMissions.length >= 3) {
      alert("최대 3개까지만 추가할 수 있습니다.");
      return;
    }

    setTodayMissions((prevMissions) => [...prevMissions, mission]);
  };

  const removeMissionFromToday = (mission: Mission) => {
    // isFinish 상태가 true인 경우 삭제 불가능
    if (mission.isFinish) {
      console.log("완료된 미션은 삭제할 수 없습니다.");
      return;
    }

    setTodayMissions((prevMissions) =>
      prevMissions.filter((item) => item.missionId !== mission.missionId)
    );
  };

  return (
    <div className="bg-[#fff7e0] px-2 py-8 h-screen">
      <div className="mt-8 grid grid-cols-2 gap-8 flex items-center ">
        <div
          className="allmission shadow-lg rounded-lg"
          style={{ overflowY: "auto", maxHeight: "70vh" }}
        >
          {missions.map((categoryMission, index) => (
            <FixMissionBox
              key={index}
              category={categoryMission.category}
              missions={categoryMission.missionBaseResList}
              addMissionToToday={addMissionToToday}
            />
          ))}
        </div>
        <div className="bg-[#f4f4f4] p-4 rounded-lg mb-4 border-2 border-gray-500 shadow-lg min-h-560 ">
          <h2 className="text-xl font-semibold mb-4">오늘의 미션</h2>
          <div className="space-y-4">
            {todayMissions.map((mission) => (
              <div
                key={mission.missionId}
                className="flex justify-between items-center"
              >
                <p>{mission.name}</p>
                <p>{mission.isFinish ? "완료" : "미완료"}</p>
                <Button
                  radius="full"
                  className={`bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg`}
                  onClick={() => removeMissionFromToday(mission)}
                  disabled={mission.isFinish} // isFinish가 true이면 버튼 비활성화
                >
                  {mission.isFinish ? "완료" : "삭제"}
                </Button>
              </div>
            ))}
          </div>
        </div>
      </div>
      <div className="mt-8 flex justify-center">
        <Link
          to="/mission"
          className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700 mt-4"
        >
          수정완료버튼
        </Link>
      </div>
    </div>
  );
}

export default Fixmission;
