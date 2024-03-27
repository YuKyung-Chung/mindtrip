import MyProgress from "../../../components/Progress/Progress"
import Testcal from "../../../components/Calendar/testcal";

function MypageProgress() {
  
  return (
    <article className="bg-[#f4c2c2] flex flex-col justify-around items-center min-h-[80vh] relative">
      <div className="bg-white rounded-lg p-4 w-4/5 flex justify-around m-4">
        <MyProgress />
      </div>
      <span className="mb-2">월간보고서</span>
      <div className="mb-4">
        <Testcal />
      </div>
    </article>
  );
}

export default MypageProgress;
