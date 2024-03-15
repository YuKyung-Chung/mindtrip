import { Avatar } from "@nextui-org/react";
import { Card } from "@nextui-org/react";
import { Link, Outlet } from "react-router-dom";



export default function Mypage() {
  return (
    <div className="max-w-6xl mx-auto my-8 p-6 bg-white rounded-lg shadow-md">
      <div className="flex justify-between">
        <div className="flex space-x-4">
          <Avatar></Avatar>
          <div className="flex flex-col justify-center">
            <span className="text-sm font-semibold text-gray-700">사용자</span>
            설정
          </div>
        </div>
        <div className="flex space-x-4">
          <div>
            <Link to={'/mypage/htp'}>HTP검사결과</Link>
            </div>
          <div><Link to={'/mypage/progress'}>진척도</Link></div>
          <div><Link to={'/mypage/history'}>내기록</Link></div>
          <div><Link to={'/mypage/fix'}>내정보수정</Link></div>
        </div>
      </div>
      <div className="mt-6  gap-4">
        <div >
          <div className="flex flex-col space-y-6">
            <Card className="w-full">
              <Outlet></Outlet>
            </Card>
          </div>
        </div>
      </div>
    </div>
  );
}

