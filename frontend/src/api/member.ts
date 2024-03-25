import axios from "axios";
import Swal from "sweetalert2";

// 로그인하기

async function login(id :string, password :string): Promise<void> {
  try {
    const res = await axios.post('https://mindtrip.site/api/members/v1/login', {
      'id' : id,
      'password' : password
    });
    // 여기에서 accessToken을 저장하자
    console.log(res.data)
    Swal.fire({
      icon:'success',
      title:'로그인 완료!'
    }).then(() =>{
      // 메인화면으로 보내주기
      location.href = '/main'
    })
  } catch (err) {
    console.log(err);
  }
}


// 회원가입하기
async function signup(id :string, password :string, nickname:string): Promise<void> {
  try {
    const res = await axios.post('https://mindtrip.site/api/members/v1/register', {
      'id' : id,
      'password' : password,
      'nickname' : nickname
    });
    // 여기에서 accessToken을 저장하자
    console.log(res.data)
    Swal.fire({
      icon:'success',
      title:'회원가입 완료!'
    }).then(()=> {
      // 결과화면으로 보내주기 -> 이후, 결과 페이지에서 로그인 여부에 따라 마을 정보 보여주기
      location.href = '/htp/result'
    })
    
  } catch (err) {
    console.log(err);
  }
}

export {login, signup}