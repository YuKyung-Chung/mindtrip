import { Avatar } from "@nextui-org/react";
import { CardHeader, CardFooter, Card } from "@nextui-org/react";
import { Link, Outlet } from "react-router-dom";



export default function Component() {
  return (
    <div className="max-w-4xl mx-auto my-8 p-6 bg-white rounded-lg shadow-md">
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
        </div>
      </div>
      <div className="mt-6 grid grid-cols-3 gap-4">
        <div className="col-span-1">
          <div className="flex flex-col space-y-4">
            내 정보 관리
            <div className="flex flex-col space-y-2"></div>
            페이스북으로 공유하기
          </div>
        </div>
        <div className="col-span-2">
          <div className="flex flex-col space-y-6">
            
            <Card className="w-full">
              <CardHeader>나의 프로젝트</CardHeader>
              <CardFooter className="flex justify-between">3강</CardFooter>
            </Card>
          </div>
        </div>
      </div>
      <Outlet></Outlet>
    </div>
  );
}

