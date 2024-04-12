// Missionbox 컴포넌트

import SuccessButton from "../../atoms/buttons/successbtn";

interface Mission {
  missionId: number;
  name: string;
  isFinish: boolean;
}

interface MissionboxProps {
  category: string;
  missions: Mission[];
}

function Missionbox({ category, missions }: MissionboxProps) {
  return (
    <div className="bg-[#FFF7ED] p-4 rounded-lg mb-4">
      <h2 className="text-xl font-semibold mb-4">{category}</h2>
      <div className="space-y-4">
        {missions.map(mission => (
          <div key={mission.missionId} className="flex justify-between items-center">
            <p>{mission.name} 함?</p>
            <SuccessButton isFinish={mission.isFinish} missionId={mission.missionId} onClick={() => {}} />
          </div>
        ))}
      </div>
    </div>
  );
}

export default Missionbox;
