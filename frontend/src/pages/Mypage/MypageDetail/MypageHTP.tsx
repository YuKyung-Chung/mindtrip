import { Button } from "@nextui-org/react";

function MypageHTP() {
  return (
    <div className="bg-[#f4c2c2] p-4 flex justify-center items-center min-h-screen relative">
      <div className="bg-white p-4 max-w-L absolute top-32">
        <h1 className="text-lg font-bold mb-4">여기에 검사결과</h1>
        <Button className="w-full">
          새 검사 하러가기
        </Button>
      </div>
      <div className="gap-4 ml-4 absolute top-1/2">
        <div>
          <div>집</div>
          <div className="flex space-x-2">
            <div className="bg-gray-300 h-20 w-32" />
            <div className="bg-gray-300 h-20 w-32" />
          </div>
        </div>
        <div>
          <div>나무</div>
          <div className="flex space-x-2">
            <div className="bg-gray-300 h-20 w-32" />
            <div className="bg-gray-300 h-20 w-32" />
          </div>
        </div>
        <div>
          <div>닝겐</div>
          <div className="flex space-x-2">
            <div className="bg-gray-300 h-20 w-32" />
            <div className="bg-gray-300 h-20 w-32" />
          </div>
        </div>
      </div>
    </div>
  );
}

export default MypageHTP;
