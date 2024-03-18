import { Button, Tooltip, Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, useDisclosure, useSelect } from "@nextui-org/react";
import ChatIcon from "../../atoms/Icons/ChatIcon";
import ChatList from "./Chat/ChatList";
import Chat from "./Chat/Chat";
import { useSelector } from "react-redux";
import { RootState } from './../../store/store'

type useDisclosureType = {
  isOpen: boolean
  onOpen: () => void
  onOpenChange: (isOpen: boolean) => void
}

function ChatModalBtn() {
  // 모달창 오픈 제어용
  const { isOpen, onOpen, onOpenChange }: useDisclosureType = useDisclosure();

  // redux용
  let chat = useSelector((state:RootState)=> state.chat)

  return (
    <div>
      <Tooltip content='대화 확인하기'>
        <Button
          isIconOnly
          size='lg'
          variant='ghost'
          className='absolute bottom-10 right-[4%] shadow-xl'
          onPress={onOpen}
        >
          <ChatIcon />
        </Button>
      </Tooltip>
      <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {() => (
            <>
              <ModalHeader>상담 모달</ModalHeader>
              <ModalBody>
                {
                  chat.isList ? (<ChatList/>) : (<Chat/>)
                }
              </ModalBody>
            </>
          )}
        </ModalContent>
      </Modal>
    </div>

  )
}

export default ChatModalBtn