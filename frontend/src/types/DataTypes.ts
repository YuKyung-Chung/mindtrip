// 고민 타입
type consultType = {
  consultId : number,
  memberId : number,
  title : string,
  content : string,
  categoryId : number
}

// 고민 카테고리 타입
type categoryType = {
  categoryId : number,
  categoryName : string
}

export type {consultType, categoryType}