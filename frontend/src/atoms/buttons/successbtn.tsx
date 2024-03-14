import  { useState } from "react";
import { Button } from "@nextui-org/react";

export default function SuccessButton() {
  // 버튼의 클릭 여부를 저장할 상태 변수
  const [isClicked, setIsClicked] = useState(false);

  // 버튼 클릭 시 호출되는 함수
  const handleButtonClick = () => {
    // 버튼이 클릭되었을 때만 동작
    if (!isClicked) {
      // 상태 변경
      setIsClicked(true);
      // TODO: 미션 완료 처리 또는 다른 작업 수행
    }
  };

  return (
    <Button
      radius="full"
      className={`bg-gradient-to-tr from-pink-500 to-yellow-500 text-white shadow-lg ${
        isClicked ? "cursor-not-allowed opacity-50" : "" // 클릭 불가능한 상태일 때 스타일 적용
      }`}
      onClick={handleButtonClick}
      disabled={isClicked} // 클릭 불가능한 상태로 설정
    >
      {isClicked ? "미션 완료" : "미션 성공"}
    </Button>
  );
}
