import { useNavigate } from 'react-router-dom';
import { Button } from "@nextui-org/react";
import {useState, useEffect} from 'react'
import PostitBtn from '../components/Main/PostitBtn';
// import MainBackground from '../components/Main/MainBackground';
import background from './../assets/background.png'
import backgroundPhone from './../assets/background_phone.png'


function Main() {
  const navigate = useNavigate()

  const [isMobile, setIsMobile] = useState<boolean>(false)

  useEffect(() => {
    // 화면 크기가 변경될 때마다 실행
    function handleResize() {
      setIsMobile(window.innerWidth < 450); // 예시로 450px 미만을 모바일로 간주
    }

    window.addEventListener('resize', handleResize); // resize 이벤트를 감지하여 handleResize 함수 실행

    // 컴포넌트가 언마운트될 때 리스너 제거
    return () => {
      window.removeEventListener('resize', handleResize);
    };

  }, [])
  return (
    <div className='h-screen relative'>
      <img src={isMobile ? backgroundPhone : background} className='absolute w-full h-full object-cover'/>
      <div className='z-10'>
        <h1>메인페이지</h1>
        <div className='absolute top-[50%] right-10 lg:top-[40%] lg:right-[20%]'>
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