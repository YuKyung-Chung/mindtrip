import MyPostit from "../../../components/MyPostit/MyPostit";

function MypageFix() {
  return (
    <div className="flex justify-center items-center min-h-screen">
      <div>
        <h1 className="text-2xl font-bold mb-4">내정보수정</h1>
        <MyPostit />
      </div>
    </div>
  );
}

export default MypageFix;
