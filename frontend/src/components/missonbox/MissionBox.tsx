import React from 'react';
import SuccessButton from "../../atoms/buttons/successbtn";

function Missionbox({ category, missions }) {
  return (
    <div className="bg-[#FFF7ED] p-4 rounded-lg mb-4">
      <h2 className="text-xl font-semibold mb-4">{category}</h2>
      <div className="space-y-4">
        {missions.map(mission => (
          <div key={mission.missionId} className="flex justify-between items-center">
            <p>{mission.name} 함?</p>
            <SuccessButton />
          </div>
        ))}
      </div>
    </div>
  );
}

export default Missionbox;
