
import BackButton from '../../atoms/backbtn';
import AlarmButton from '../../atoms/alambtn';
import Successbtn from '../../atoms/successbtn';

export default function Fixmission() {
  return (
    <div className="bg-white p-8">
      <div className="flex flex-col justify-center items-center mb-6">
      <BackButton />
        <AlarmButton />
        <h1 className="text-3xl font-bold mt-20">미션 수정하기</h1>
      </div>
      <div className="mt-8 grid grid-cols-2 gap-8">
        <div>
          <div className="bg-[#FFF7ED] p-4 rounded-lg">
            <h2 className="text-xl font-semibold mb-4">초보 미션</h2>
            <div className="space-y-4">
              <div className="flex justify-between items-center">
                <p>일어나서 좋아하는 노래 듣기</p>
                
              </div>
              <div className="flex justify-between items-center">
                <p>산책하기</p>
                <Successbtn />                
              </div>
              <div className="flex justify-between items-center">
                <p>반신욕하기</p>
                
              </div>
            </div>
          </div>
          <div className="bg-[#FFF7ED] p-4 rounded-lg mt-4">
            <h2 className="text-xl font-semibold mb-4">솔로 미션</h2>
            <div className="space-y-4">
              <div className="flex justify-between items-center">
                <p>책 한권 클리어하기</p>
                
              </div>
              <div className="flex justify-between items-center">
                <p>전자기기 정리</p>
                
              </div>
              <div className="flex justify-between items-center">
                <p>반신욕하기</p>
                
              </div>
            </div>
          </div>
          <div className="bg-[#FFF7ED] p-4 rounded-lg mt-4">
            <h2 className="text-xl font-semibold mb-4">건강 미션</h2>
            <div className="flex justify-between items-center">
              <p>영양제 챙기기</p>
              
            </div>
          </div>
        </div>
        <div className="bg-[#FFF7ED] p-4 rounded-lg">
          <h2 className="text-xl font-semibold mb-4">셀프테스트 미션</h2>
          <div className="space-y-4">
            <div className="flex justify-between items-center">
              <p>일어나서 좋아하는 노래 듣기</p>
              
            </div>
            <div className="flex justify-between items-center">
              <p>산책하기</p>
      
            </div>
            <div className="flex justify-between items-center">
              <p>반신욕하기</p>
      
            </div>
          </div>
        </div>
      </div>
      <div className="mt-8 flex justify-center">

      </div>
    </div>
  )
}

