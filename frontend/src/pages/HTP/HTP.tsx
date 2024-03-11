import { Outlet } from "react-router-dom"
// HTP 검사들이 들어갈 틀,  Outlet을 이용해서 나무, 집, 사람을 나눠주자
// 굳이 이렇게 한 이유? 3D 이미지를 하나만 써서 이어지게 할꺼기 때문에,,,

function HTP() {
  return(
    <div className="md:container md:mx-auto min-h-screen w-full">
      <Outlet/>
    </div>
  )
}

export default HTP