import { Card, CardBody, Button } from "@nextui-org/react";
import {useState} from 'react'

function SharedConsult() {
  const [hover, sethover] = useState<boolean>(false)

  return(
    <Card 
      shadow={hover ? 'sm' : 'none'} 
      onMouseEnter={() => {sethover(true)}} 
      onMouseLeave={() => {sethover(false)}}
      className="bg-amber-50"
    >
      <CardBody>
        <p className="text-lg">고민 제목입니다.</p>
        <div className="h-10 text-ellipsis overflow-hidden my-1 mb-2">
          <p className="text-sm">이건 공유된 고민이야 공유된 고민이야 공유된 고민이야</p>
        </div>
        <Button variant='light' color='warning' size='sm' className="mt-2 w-10 ml-[80px]" startContent={
          <NotLike/>
        }>20</Button>
      </CardBody>
    </Card>
  )
}

export default SharedConsult

function NotLike() {
  return(
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
      <path strokeLinecap="round" strokeLinejoin="round" d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z" />
    </svg>
  

  )
}