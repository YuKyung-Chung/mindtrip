import { useState } from 'react';
import { Avatar } from "@nextui-org/react";
import { Card } from "@nextui-org/react";
import { Link, Outlet } from "react-router-dom";

function Mypage() {
  const [menuOpen, setMenuOpen] = useState(false);

  const toggleMenu = () => {
    setMenuOpen(!menuOpen);
  };

  return (
    <div className="max-w-6xl mx-auto my-8 p-6 bg-white rounded-lg shadow-md">
      <div className="flex justify-between">
        <div className="flex space-x-4">
          <Avatar />
          <div className="flex flex-col justify-center">
            <span className="text-sm font-semibold text-gray-700">나의마을 : 마을이름</span>
            사용자님 환영합니다.
          </div>
        </div>
        {/* 햄버거 메뉴 - 작은 화면에서만 표시 */}
        <div className="md:hidden">
          <button onClick={toggleMenu} className="text-gray-600 hover:text-gray-800 focus:outline-none">
            <svg className="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
            </svg>
          </button>
        </div>
        {/* 햄버거 메뉴 - 작은 화면에서만 표시 */}
        {/* 메뉴 아이템 - 큰 화면에서 표시 */}
        <div className="hidden md:flex space-x-4">
          <div><Link to={'/mypage/htp'} className="text-blue-500 hover:bg-gray-200 rounded-md px-3 py-1">HTP검사결과</Link></div>
          <div><Link to={'/mypage/progress'} className="text-blue-500 hover:bg-gray-200 rounded-md px-3 py-1">진척도</Link></div>
          <div><Link to={'/mypage/history'} className="text-blue-500 hover:bg-gray-200 rounded-md px-3 py-1">내기록</Link></div>
          <div><Link to={'/mypage/fix'} className="text-blue-500 hover:bg-gray-200 rounded-md px-3 py-1">내정보수정</Link></div>
        </div>
        {/* 메뉴 아이템 - 큰 화면에서 표시 */}
      </div>
      {/* 햄버거 메뉴를 열었을 때 표시되는 아이템들 - 작은 화면에서만 표시 */}
      <div className={`md:hidden ${menuOpen ? 'block' : 'hidden'}`}>
        <div className="flex flex-col space-y-4">
          <Link to={'/mypage/htp'} className="text-blue-500 hover:bg-gray-200 rounded-md px-3 py-1">HTP검사결과</Link>
          <Link to={'/mypage/progress'} className="text-blue-500 hover:bg-gray-200 rounded-md px-3 py-1">진척도</Link>
          <Link to={'/mypage/history'} className="text-blue-500 hover:bg-gray-200 rounded-md px-3 py-1">내기록</Link>
          <Link to={'/mypage/fix'} className="text-blue-500 hover:bg-gray-200 rounded-md px-3 py-1">내정보수정</Link>
        </div>
      </div>
      {/* 햄버거 메뉴를 열었을 때 표시되는 아이템들 - 작은 화면에서만 표시 */}
      <div className="mt-6 gap-4">
        <div>
          <div className="flex flex-col space-y-6">
            <Card className="w-full">
              <Outlet />
            </Card>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Mypage;
