import { Card, CardBody, Button } from "@nextui-org/react";
import {useState} from 'react'
import EmptyHeart from "../../atoms/Icons/EmptyHeart";
import FullHeart from "../../atoms/Icons/FullHeart";
import { villageBackgroundColor, villageTextColor } from "../../atoms/color"
import { useSelector } from "react-redux";
import { RootState } from "../../store/store";

function SharedConsult() {
  const [like, setLike] = useState<boolean>(false)

  let member = useSelector((state: RootState) => state.member)

  return(
    <Card 
      shadow='sm' 
      className={`${villageBackgroundColor[member.villageName] || ''} border-1 border-gray-200 h-36 hover:cursor hover:scale-[1.03]`}
    >
      <CardBody className="relative">
        <p className="text-lg">고민 제목입니다.</p>
        <div className="h-14 text-ellipsis overflow-hidden my-1 mb-2">
          <p className="text-sm">이건 공유된 고민이야 공유된 고민이야 공유된 고민이야</p>
        </div>
        <Button 
          size='sm'
          className={`${villageTextColor[member.villageName] || ''} bg-transparent max-w-16 min-w-14 absolute bottom-3 right-3`} 
          startContent={like ? <FullHeart/> : <EmptyHeart/>}
          onClick={() => setLike(!like)}
        >{like ? 21 : 20}</Button>
      </CardBody>
    </Card>
  )
}

export default SharedConsult