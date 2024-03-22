import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

function Result() {
  return(
  <div className="">
      <p className="text-center text-4xl py-10">결과 두두둥</p>
      <div className="w-full lg:w-[70vw] mx-auto text-center border-3 rounded-md bg-white min-h-[65vh] p-5">
        <p className="min-h-[55vh]">결과는 여기에 들어감</p>
      </div>
    <MyBtn/>
  </div>
  )
}

export default Result

function MyBtn() {
  const navigate = useNavigate()

  const checkVillage = () :void => {
    Swal.fire({
      title: '당신은 오렌지 마을입니다~'
    }).then(() => {
      navigate('/main')
    })
  }

  return(
    <button
      className="fixed bottom-10 left-1/2 translate-x-[-50%] py-2 px-8 h-14 w-48 text-black text-base font-bold nded-full overflow-hidden bg-white rounded-full transition-all duration-400 ease-in-out shadow-md hover:scale-105 hover:text-white hover:shadow-lg active:scale-90 before:absolute before:top-0 before:-left-full before:w-full before:h-full before:bg-gradient-to-r before:from-blue-500 before:to-blue-300 before:transition-all before:duration-500 before:ease-in-out before:z-[-1] before:rounded-full hover:before:left-0"
      onClick={checkVillage}
    >
      <p>내 마을 확인하기</p>
    </button>
  )
}