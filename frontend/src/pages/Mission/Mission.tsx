import { Link } from 'react-router-dom';
import BackButton from '../../atoms/backbtn';
import AlarmButton from '../../atoms/alambtn';
import Successbtn from '../../atoms/successbtn';

export default function Mission() {
  return (
    <div className="bg-[#fff7e0] px-2 py-8">
      <div className="flex flex-col justify-center items-center mb-6">
        <BackButton />
        <AlarmButton />
        <h1 className="text-5xl font-bold mt-20">오늘의 미션</h1>
      </div>
      <div className="flex justify-end text-sm mb-4 w-4/5 mx-auto">
        <span>달성률: 1/3</span>
      </div>
      <div className="space-y-4">
        <div className="flex justify-between items-center p-2 bg-white rounded-lg shadow w-4/5 mx-auto">
          <span className="text-lg">좋아하는 노래틀기</span>
          <Successbtn />
        </div>
        <div className="flex justify-between items-center p-2 bg-white rounded-lg shadow w-4/5 mx-auto">
          <span className="text-lg">잠자리 정리</span>
          <Successbtn />
        </div>
        <div className="flex justify-between items-center p-2 bg-white rounded-lg shadow w-4/5 mx-auto">
          <span className="text-lg">친구나 가족에게 전화하기</span>
          <Successbtn />
        </div>
      </div>
      <div className="mt-8 p-2  rounded-lg text-center w-4/5 mx-auto">
        <p className="mb-4">오늘의 미션이 맘에 들지 않았다면,</p>
      
        <Link to="/fixmission" className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700 mt-4">
        수정완료버튼
      </Link>
      </div>
    </div>
  );
}
