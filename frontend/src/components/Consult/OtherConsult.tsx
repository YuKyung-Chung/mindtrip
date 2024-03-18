import { Card, CardBody, Button } from "@nextui-org/react";
import {useState} from 'react'

function OtherConsult() {
  const [hover, sethover] = useState<boolean>(false)

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
        <Button color='warning' variant="ghost" className="max-w-40 min-w-32 absolute bottom-3 right-7">상담하기</Button>
      </CardBody>
    </Card>
  )
}

export default OtherConsult