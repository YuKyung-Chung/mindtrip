import axios from "axios";
import { memberType } from "../types/DataTypes";

// 로그인하기

async function login(id :string, password :string): Promise<string|void> {
  try {
    const res = await axios.post('https://mindtrip.site/api/members/v1/login', {
      'id' : id,
      'password' : password
    });
    return res.data.result
  } catch (err) {
    console.log(err);
  }
}



// 유저정보 로딩하기
async function loadUser(token:string): Promise<memberType|void> {
  try{
    const res = await axios.get('https://mindtrip.site/api/members/v1/1',{
      headers: {
        Authorization: token
      }
    })
    console.log(res.data.result)
    return res.data.result
    // 여기서 멤버 정보를 return 해줘야함
  } catch (err) {
    console.log(err)
  }
}


// 회원가입하기
async function signup(id :string, password :string, nickname:string): Promise<boolean> {
  try {
    const res = await axios.post('https://mindtrip.site/api/members/v1/register', {
      'id' : id,
      'password' : password,
      'nickname' : nickname
    });
    // 여기에서 accessToken을 저장하자
    console.log(res.data)
    return true
  } catch (err) {
    console.log(err);
    return false
  }
}

export {login, signup, loadUser}