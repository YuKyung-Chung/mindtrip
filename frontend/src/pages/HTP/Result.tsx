import { Card, CardBody } from "@nextui-org/react";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

function Result() {
  return(
  <Card className="h-full m-auto mx-auto xs:w-full md:w-4/5 xl:w-2/3" >
    <CardBody className="flex-col content-center">
      <p className="text-center text-4xl m-10">결과 두두둥</p>
      <div className="text-center border-3 rounded-md border-indigo-200 min-h-[65vh] mt-0 m-5 p-6">
        <p className="min-h-[55vh]">결과는 여기에 들어감</p>
      </div>
    </CardBody>
    <MyBtn/>
  </Card>
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