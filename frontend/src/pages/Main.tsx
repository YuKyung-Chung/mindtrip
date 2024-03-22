import { useNavigate } from 'react-router-dom';
import { Button } from "@nextui-org/react";
import PostitBtn from '../components/Main/PostitBtn';
// import MainBackground from '../components/Main/MainBackground';
import background from './../assets/background.png'

function Main() {
  const navigate = useNavigate()

  return (
    <div className='h-screen relative'>
      <img src={background} className='absolute w-full h-full'/>
      <div className='z-10'>
        <h1>메인페이지</h1>
        <div className='absolute top-[250px] right-[350px]'>
          <PostitBtn />
        </div>

        <Button onClick={() => navigate('/consult')}>고민상담소</Button>
        <Button onClick={() => navigate('/mission')}>데일리 미션</Button>
        <Button onClick={() => navigate('/mypage/htp')}>마이페이지</Button>
      </div>
    </div>
  )
}

export default Main