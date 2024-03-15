import { useNavigate } from 'react-router-dom';
import {Button } from "@nextui-org/react";
import PostitBtn from '../components/Main/PostitBtn';

function Main () {
  const navigate = useNavigate()
  return(
    <div className='bg-slate-200 h-screen relative'>
      <h1>메인페이지</h1>
      <div className='absolute top-10 right-10'>
        <PostitBtn />
      </div>
      
      {/* 임시 */}
      <Button onClick={() => navigate('/consult')}>고민상담소</Button>
      <Button onClick={() => navigate('/mission')}>데일리 미션</Button>
      <Button onClick={() => navigate('/mypage')}>마이페이지</Button>
    </div>
  )
}

export default Main