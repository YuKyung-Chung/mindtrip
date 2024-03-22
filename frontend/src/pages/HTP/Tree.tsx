import { useState, useRef, useEffect } from "react"
import { Button } from "@nextui-org/react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import Draw from "../../components/HTP/Draw";

// props의 타입을 지정해주자
type propsType = {
  goNext: () => void
  qna :{q:string, ans:string[]}
  isLast :boolean
}
type propsType1 = {
  goSurvey: () => void
}

// 나무 그림 그리고, 설문조사가 나오는 페이지
function Tree() {
  const [order, setOrder] = useState<number>(0)
  const goNext: () => void = () => {
    setOrder(order + 1);
  };

  const [isSurvey, setIsSurvey] = useState<boolean>(false)
  const goSurvey = function() :void {
    setIsSurvey(true)
  }

  // 질문과 선택지
  const survey :{q :string, ans :string[]}[] = [
    {q: '이 나무는 몇 살 정도 되었나요?' , ans: ['어린 나무', '어른 나무', '노인 나무']},
    {q: '이 나무는 어떤 나무인가요?', ans: ['단풍 나무', '은행 나무', '벚꽃 나무']},
    {q: '이 나무의 건강은 어떤가요?', ans: ['건강해요', '허약해요', '힘들어요' ,'질문지가 더 있어도 나오나요?']},
    {q: '이 나무는 나중에 어떻게 될 것 같나요?', ans: ['쑥쑥 자라요', '질문지가 두 개면 어떻게 나오나요?']},
  ]


  return (
    <div>
      {isSurvey === false && <TreeDraw goSurvey={goSurvey}/>}
      {(isSurvey === true && order === 0)&& <TreeSurvey goNext={goNext} qna={survey[0]} isLast={false}/>}
      {(isSurvey === true && order === 1) && <TreeSurvey goNext={goNext} qna={survey[1]} isLast={false}/>}
      {(isSurvey === true && order === 2) && <TreeSurvey goNext={goNext} qna={survey[2]} isLast={false}/>}
      {(isSurvey === true && order === 3) && <TreeSurvey goNext={goNext} qna={survey[3]} isLast={true}/>}
    </div>
  )
}
export default Tree

function TreeDraw({goSurvey} :propsType1){
  // 파일 제어용
  const [file, setFile] = useState<File|null>(null)
  const fileInput = useRef<HTMLInputElement>(null);
  const handleChange = (event :React.ChangeEvent<HTMLInputElement>) => {
    // 선택한 파일 정보를 저장
    const files = event.target.files
    if (files && files[0]) {
      setFile(files[0])
    }
  };
  // 파일 써먹을려면
  useEffect(()=> {
    if (file != null) {
      console.log(file)
      Swal.fire({
        title: '업로드완료',
        icon: "success"
      }).then(() => {goSurvey()})
    }
  }, [file])
  return(
<div className="flex h-svh w-svh justify-center items-center flex-col">
        <p className="text-center mb-8 font-bold text-3xl">나무을 그려주세요.</p>
        <div className="relative border-2 rounded h-2/3 lg:w-2/3 w-full bg-white">
          <Draw/>
          <Button className="absolute bottom-0 right-0 m-3" onClick={goSurvey}>다 그렸어요</Button>
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

function TreeSurvey({ goNext, qna, isLast }: propsType) {
  const navigate = useNavigate()

   return (
    <div className="flex h-svh w-svh justify-center items-center flex-col">
      <p className="text-center mb-10 font-bold text-3xl">{qna.q}</p>
      {
        qna.ans.map((item, idx) => {
          return(
            <Button
              key={idx}
              variant="bordered"
              className='w-4/5 lg:w-3/5 m-3 h-16 text-xl bg-white hover:bg-sky-800 hover:text-white shadow'
              onClick={() => { isLast ? navigate('/htp/person') : goNext() }}
            >
              {item}
            </Button>
          )
        })
      }
    </div>
  )
}