import { useState, useRef, useEffect } from "react"
import { Button } from "@nextui-org/react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import Draw from "../../components/HTP/Draw";


// props의 타입을 지정해주자
type propsType = {
  // goNext: React.MouseEventHandler<HTMLButtonElement>
  goNext: () => void
}

// 집 그림 그리고, 설문조사가 나오는 페이지

function House() {
  const [order, setOrder] = useState<number>(0)
  const goNext: () => void = () => {
    setOrder(order + 1);
  };


  return (
    <div>
      {order === 0 && <House0 goNext={goNext} />}
      {order === 1 && <House1 goNext={goNext} />}
      {order === 2 && <House2 />}
    </div>
  )
}

export default House

function House0({ goNext }: propsType) {
  const [file, setFile] = useState<File|null>(null)
  const fileInput = useRef<HTMLInputElement>(null);
  const handleChange = (event :React.ChangeEvent<HTMLInputElement>) => {
    // 선택한 파일 정보를 저장
    const files = event.target.files
    if (files && files[0]) {
      setFile(files[0])
    }
  };

  // 써먹을려면
  useEffect(()=> {
    if (file != null) {
      console.log(file)
      Swal.fire({
        title: '업로드완료',
        icon: "success"
      }).then((r) => {goNext()})
    }
  }, [file])

  return (
    <div className="flex h-svh w-svh justify-center items-center flex-col">
      <p className="text-center mb-8 font-bold text-3xl">집을 그려주세요.</p>
      <div className="relative border-2 rounded h-2/3 w-5/6">
        <Draw />
        <Button className="absolute bottom-0 right-0 m-3" onClick={goNext}>다 그렸어요</Button>
      </div>
      <div className="flex items-center text-slate-500 mt-2">
        <p className="mr-3">그리기 힘들다면?</p>
        {/* 업로드 아이콘 */}
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
          <path strokeLinecap="round" strokeLinejoin="round" d="M12 16.5V9.75m0 0 3 3m-3-3-3 3M6.75 19.5a4.5 4.5 0 0 1-1.41-8.775 5.25 5.25 0 0 1 10.233-2.33 3 3 0 0 1 3.758 3.848A3.752 3.752 0 0 1 18 19.5H6.75Z" />
        </svg>
        <button onClick={() => {fileInput.current?.click()}}>
          <p className="underline ml-1 hover:text-violet-500 hover:cursor-pointer">업로드하기</p>
        </button>
      </div>
      {/* png형식의 파일만 올릴 수 있도록 함 */}
      <input type="file" accept=".png" ref={fileInput} onChange={handleChange} style={{display:'none'}}/>
    </div>
  )
}


function House1({ goNext }: propsType) {
  const answers :string[] = ['내가 혼자 살고 있어요', '나와 가족이 같이 살고 있어요', '아무도 살고 있지 않아요']
   return (
    <div className="flex h-svh w-svh justify-center items-center flex-col">
      <p className="text-center mb-10 font-bold text-3xl">이 집에는 누가 살고 있나요?</p>
      {
        answers.map((item, idx) => {
          return(
            <Button className='w-4/5 m-3 h-16 text-xl' onClick={goNext} key={idx} variant="flat">{item}</Button>
          )
        })
      }
    </div>
  )
}

function House2() {
  // 마지막 질문은 다음 컨텐츠로 넘겨주기 위해 navigate를 사용
  const navigate = useNavigate()

  const answers :string[] = ['내가 혼자 살고 있어요', '나와 가족이 같이 살고 있어요', '아무도 살고 있지 않아요']

   return (
    <div className="flex h-svh w-svh justify-center items-center flex-col">
      <p className="text-center mb-10 font-bold text-3xl">두번째 질문입니다</p>
      {
        answers.map((item, idx) => {
          return(
            <Button className='w-4/5 m-3 h-16 text-xl' onClick={() => {navigate('/htp/tree')}} key={idx} variant="flat">{item}</Button>
          )
        })
      }
    </div>
  )
}