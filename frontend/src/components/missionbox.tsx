import React from "react";
import SuccessButton from "../atoms/buttons/successbtn";

interface MissionboxProps {
  
}

const Missionbox: React.FC<MissionboxProps> = () => {
  return (
    <div className="bg-[#FFF7ED] p-4 rounded-lg mb-4">
      <h2 className="text-xl font-semibold mb-4">초보 미션</h2>
      <div className="space-y-4">
        <div className="flex justify-between items-center">
          <p>일어나서 좋아하는 노래 듣기</p>
        </div>
        <div className="flex justify-between items-center">
          <p>산책하기</p>
          <SuccessButton />
        </div>
        <div className="flex justify-between items-center">
          <p>반신욕하기</p>
        </div>
      </div>
    </div>
  );
};

export default Missionbox;
