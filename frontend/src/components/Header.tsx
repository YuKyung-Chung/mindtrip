import HomeIcon from "../atoms/Icons/HomeIcon"
import MessageIcon from "../atoms/Icons/MessageIcon"
import { useNavigate } from "react-router-dom"
import {Button, Card} from "@nextui-org/react";
import { useEffect, useState } from "react";

function Header() {
  const navigate = useNavigate()
  const [openMessage, setOpenMessage] = useState<Boolean>(false)
  useEffect(() => {
    setOpenMessage(false)
  }, [])

  // useEffect(() => {
  //   const sseEvents = new EventSource('')
  // })

  
  return(
    <div className="relactive">
      <div className="h-[9vh] flex justify-between px-5 items-center">
        <Button 
          isIconOnly 
          variant="light"
          onClick={() => {navigate('/main')}}
        >
          <HomeIcon />
        </Button>
        <Button 
          isIconOnly 
          variant="light"
          onClick={() => {setOpenMessage(!openMessage)}}
        >
          <MessageIcon />
        </Button>
      </div>
      {/* 메세지창 */}
      <Card 
        className={`${openMessage ? '' : 'hidden'} 
        absolute w-[80vw] h-[30vh] p-3
        bg-white top-[8vh] right-[2vw] z-20`}
      >
        받은 메세지들이 나올 곳들
      </Card>
    </div>
    
  )
}

export default Header