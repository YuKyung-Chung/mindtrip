import { Card, CardBody, Button, Modal, ModalContent, ModalHeader, ModalBody, useDisclosure } from "@nextui-org/react";
import {useState} from 'react'
import { toggleOpen, changeList } from "./../../store/store";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from './../../store/store'


// 다른 사람의 고민 하나의 컴포넌트
// 고민 제목과 내용, 그리고 상담하기 버튼이 들어감
// 상담하기 버튼을 누르면 전체 내용, 참여 여부를 확인하는 모달이 뜨고
// 참여하기를 누르면 바로 채팅방으로 넘어가야함


// 모달 제어용 타입 지정
type useDisclosureType = {
  isOpen: boolean
  onOpen: () => void
  onOpenChange: (isOpen: boolean) => void
}

function OtherConsult() {
  // 호버 제어용
  const [hover, sethover] = useState<boolean>(false)
  
  // 모달창 오픈 제어용
  const { isOpen, onOpen, onOpenChange }: useDisclosureType = useDisclosure();

  // 채팅창 관련 가져오기
  let chat = useSelector((state:RootState)=> state.chat)
  const dispatch = useDispatch()

  // 고민방 참여
  const enterChat = function() {
    // 만약 채팅창이 닫혀있는 상태라면 열어주고
    if (chat.isOpen === false) {
      dispatch(toggleOpen())
    } 
    // 채팅방으로 옮겨주자
    dispatch(changeList(false))
  }


  return (
    <Card 
      shadow={hover ? 'sm' : 'none'} 
      onMouseEnter={() => {sethover(true)}} 
      onMouseLeave={() => {sethover(false)}}
      className="bg-amber-50 h-full"
    >
      <CardBody className="relative">
        <p className="text-lg">고민 제목입니다.</p>
        <div className="h-14 text-ellipsis overflow-hidden my-1 mb-2">
          <p className="text-sm">고민 내용이 들어갑니다. 고민 내용이 들어갑니다. 고민 내용이 들어갑니다. 고민 내용이 들어갑니다.</p>
        </div>
        <Button 
          color='warning' 
          variant="ghost" 
          onPress={onOpen}
          className="max-w-40 min-w-32 absolute bottom-3 right-7"
        >상담하기</Button>
      </CardBody>
      <Modal size="sm" placement='center' isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader>닉네임 님의 고민</ModalHeader>
              <ModalBody className="flex-col items-center">
                <div className="min-h-[25vh]">
                  고민내용 들어가요
                </div>
                <Button className="w-36" onClick={() => {
                  onClose()
                  enterChat()
                  }}>참여할ㄲㅔ</Button>
                <div className="text-center text-xs mt-1 mb-5">
                  오늘 남은 참여횟수는 <span className="text-red">5회</span>입니다.
                </div>
              </ModalBody>
            </>
          )}
        </ModalContent>
      </Modal>
    </Card>
  )
}

export default OtherConsult