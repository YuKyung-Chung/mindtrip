import { Card, CardBody, Button } from "@nextui-org/react";
import {useState} from 'react'

function OtherConsult() {
  const [hover, sethover] = useState<boolean>(false)

  return (
    <Card 
      shadow={hover ? 'sm' : 'none'} 
      onMouseEnter={() => {sethover(true)}} 
      onMouseLeave={() => {sethover(false)}}
      className="bg-amber-50"
    >
      <CardBody>
        <p className="text-lg">고민 제목입니다.</p>
        <div className="h-20 text-ellipsis overflow-hidden my-1 mb-2">
          <p className="text-sm">고민고민고민고민내용내용고민고민고민고민내용내용고민고민고민고민내용내용고민고민고민고민내용내용고민고민고민고민내용내용고민고민고민고민내용내용고민고민고민고민내용내용고민고민고민고민내용내용</p>
        </div>
        <Button color='warning' variant="ghost" className="mt-2">상담하기</Button>
      </CardBody>
    </Card>
  )
}

export default OtherConsult