import axios from "axios";
import { consultType, categoryType } from "../types/DataTypes";
import Swal from "sweetalert2";

// 입장가능한 고민 목록 가져오기
async function getConsults(token:string): Promise<consultType[]> {
  try {
    const res = await axios.get('https://mindtrip.site/api/consults/v1/available', {
      headers: {
        // Authorization: token
        'x-member-id': 1
      }
    });
    console.log(token)
    return res.data.result.consultList;
  } catch (err) {
    console.log(err);
    return [];
  }
}


// 고민 등록하기
async function uploadConsult(title :string, content :string, categoryId: number|string|categoryType) :Promise<void> {
  try{
    await axios.post('https://mindtrip.site/api/consults/v1', {
      'title' : title,
      'content' : content,
      'categoryId' : categoryId
    }, {
      headers: {
        'x-member-id': 1
      }
    })
    Swal.fire({
      title: '등록완료~',
      icon: 'success'
    }).then(() => {
      location.reload()
    })
  } catch(err) {
    console.log(err)
  }
}

// 카테고리 가져오기
async function getCategory(): Promise<categoryType[]>{
  try{
    const res = await axios.get('https://mindtrip.site/api/consults/v1/category', {
      headers: {
        'x-member-id': 1
      }
    })
    return res.data.result.consultCategoryList
  } catch(err) {
    console.log(err)
    return []
  }
}


// 공유된 고민 가져오기
// 입장가능한 고민 목록 가져오기
async function getSharedConsult(token:string): Promise<consultType[]> {
  try {
    const res = await axios.get('https://mindtrip.site/api/consults/v1/shared', {
      headers: {
        // Authorization: token
        'x-member-id': 1
      }
    });
    console.log(token)
    return res.data.result.consultList;
  } catch (err) {
    console.log(err);
    return [];
  }
}

export {getConsults, uploadConsult, getCategory, getSharedConsult}