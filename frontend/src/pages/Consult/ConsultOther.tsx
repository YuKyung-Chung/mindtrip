import { useState, useEffect } from 'react'
import { Button, Select, SelectItem, Tooltip, Card, Modal, ModalContent, ModalHeader, ModalBody, useDisclosure } from "@nextui-org/react";
import OtherConsult from '../../components/Consult/OtherConsult';
import { useSelector, useDispatch } from "react-redux";
import { RootState } from './../../store/store'
import { toggleOpen } from '../../store/chatSlice';
import ChatIcon from './../../atoms/Icons/ChatIcon'
import XIcon from '../../atoms/Icons/XIcon';
import PencilIcon from '../../atoms/Icons/PencilIcon';
import Chat from '../../components/Consult/Chat/Chat';
import { consultType, categoryType } from '../../types/DataTypes';
import { getConsults } from '../../api/consults';
import CreateNewConsult from '../../components/Consult/CreateNewConsult';

// 모달 제어용 타입 지정
type useDisclosureType = {
  isOpen: boolean
  onOpen: () => void
  onOpenChange: (isOpen: boolean) => void
}

function ConsultOther() {
  const dispatch = useDispatch()

  // 카테고리 받기
  let category = useSelector((state: RootState) => state.consultSlice.category)

  // 선택된 카테고리
  const [selectedCategory, setSelectedCategory] = useState<categoryType | null>(null)
  const handleCategory = (e: any) => {
    setSelectedCategory(e.target.value)
    console.log(selectedCategory)
  }

  // 고민 받아서 저장할 변수
  const [otherConsults, setOtherConsult] = useState<consultType[]>([])

  useEffect(() => {
    // 전체 고민 가져오기
    const fetchConsult = async () => {
      try {
        let tempOtherConsult: consultType[] = await getConsults()
        setOtherConsult(tempOtherConsult)
        console.log(tempOtherConsult)
      } catch (err) {
        console.log(err)
      }
    }
    fetchConsult()
  }, [])

  // 채팅 관련 가져오기
  let chat = useSelector((state: RootState) => state.chat)

  // 모달창 오픈 제어용
  const { isOpen, onOpen, onOpenChange }: useDisclosureType = useDisclosure();

  return (
    <div className="w-full lg:w-3/4 mx-auto h-screen pt-5">
      <div className="py-5 px-3 min-h-[40%]">
        <p className="text-2xl hover:cursor-pointer">다른 사람들의 고민 보기<span className='text-sm'>(오늘 남은 횟수: 5)</span></p>
        <div className="flex justify-between items-center mt-4 mb-2">
          {/* 카테고리들 */}
          <Select
            label='카테고리 선택'
            size='sm'
            onChange={handleCategory}
            className='w-[150px]'
          >
            {category.map((oneCategory: categoryType) => {
              return (
                <SelectItem key={oneCategory.categoryId}>
                  {oneCategory.categoryName}
                </SelectItem>
              )
            })
            }
          </Select>

          {/* 내 고민 작성하기 */}
          <Tooltip content='내 고민 작성하기'>
            <Button isIconOnly variant='light' onPress={onOpen}><PencilIcon /></Button>
          </Tooltip>
        </div>
        <div className='mt-2 flex-col'>
          {
            otherConsults.map((consult, idx) => (
              <div className="w-full p-2 h-44" key={idx}>
                <OtherConsult consult={consult} />
              </div>
            ))
          }
        </div>
      </div>
      {/* 채팅 창 여는 버튼 */}
      <Tooltip content={chat.isOpen ? '닫기' : '대화 확인하기'} placement={chat.isOpen ? 'right' : 'top'}>
        <Button
          isIconOnly
          size='lg'
          radius='full'
          variant={chat.isOpen ? 'solid' : 'faded'}
          onClick={() => dispatch(toggleOpen())}
          className='fixed bottom-10 right-[4%] shadow-xl'
        >
          {chat.isOpen ? <XIcon /> : <ChatIcon />}
        </Button>
      </Tooltip>
      {/* 채팅창 */}
      <Card
        style={{
          display: chat.isOpen ? 'block' : 'none',
        }}
        className='fixed top-[23%] right-[5%] w-[80%] h-[70%]
          sm:top-[20%] w-96 h-[65%] p-5'
      >
        <Chat />
      </Card>
      <Modal size="sm" placement='center' isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader>내 고민 공유하기</ModalHeader>
              <ModalBody>
                <CreateNewConsult onClose={onClose} category={category} />
              </ModalBody>
            </>
          )}
        </ModalContent>
      </Modal>
    </div>
  )
}

export default ConsultOther