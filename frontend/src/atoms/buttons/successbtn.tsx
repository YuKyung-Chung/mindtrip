import { useState } from "react";
import axios from "axios";
import { Button } from "@nextui-org/react";

interface SuccessButtonProps {
  isFinish: boolean;
  missionId: number; // 미션 ID를 받아옴
  onClick: () => void;
}

function SuccessButton({ isFinish, missionId }: SuccessButtonProps) {
  // isFinish 값을 초기 상태로 설정
  const [isClicked, setIsClicked] = useState(isFinish);

  // 버튼 클릭 시 호출되는 함수
  const handleButtonClick = async () => {
    try {
      // 미션 완료 처리를 위한 POST 요청 보내기
      await axios.post(
        `https://mindtrip.site/api/missions/v1/mytable/${missionId}`,
        { isFinish: true },
        {
          headers: {
            "Content-Type": "application/json",
            "x-member-id": "1"
          }
        }
      );
      // 상태 변경
      setIsClicked(true);
      // TODO: 미션 완료 처리 또는 다른 작업 수행
    } catch (error) {
      console.error("Error completing mission:", error);
    }
  };

  return (
    <Button
      radius="full"
      className={`bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg ${
        isClicked ? "cursor-not-allowed opacity-50" : ""
      }`}
      onClick={handleButtonClick}
      disabled={isClicked}
    >
      {isClicked ? "미션 완료" : "미션 성공"}
    </Button>
  );
}
export default SuccessButton