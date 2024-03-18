import { Card, CardBody, Button } from "@nextui-org/react";
import {useState} from 'react'
import EmptyHeart from "../../atoms/Icons/EmptyHeart";
import FullHeart from "../../atoms/Icons/FullHeart";

function SharedConsult() {
  const [hover, sethover] = useState<boolean>(false)
  const [like, setLike] = useState<boolean>(false)

  return(
    <Card 
      shadow={hover ? 'sm' : 'none'} 
      onMouseEnter={() => {sethover(true)}} 
      onMouseLeave={() => {sethover(false)}}
      className="bg-amber-50 h-36 hover:cursor"
    >
      <CardBody className="relative">
        <p className="text-lg">고민 제목입니다.</p>
        <div className="h-14 text-ellipsis overflow-hidden my-1 mb-2">
          <p className="text-sm">이건 공유된 고민이야 공유된 고민이야 공유된 고민이야</p>
        </div>
        <Button 
          variant='light' 
          color='warning' 
          size='sm' 
          className="max-w-16 min-w-14 absolute bottom-3 right-3" 
          startContent={like ? <FullHeart/> : <EmptyHeart/>}
          onClick={() => setLike(!like)}
        >{like ? 21 : 20}</Button>
      </CardBody>
    </Card>
  )
}

export default SharedConsult