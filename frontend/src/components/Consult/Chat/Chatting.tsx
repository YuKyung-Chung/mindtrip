import { temp } from '../../../store/store'
import { useDispatch } from "react-redux"

type propstype = {
  title: string,
  content: string,
  alert: number
}

function Chatting({title, content, alert} :propstype) {
  const dispatch = useDispatch()

  return(
    <div className="relative border-b h-24 p-3" onClick={() => dispatch(temp())}>
      <p className="text-lg">{title}</p>
      <p className="text-sm overflow-hidden">{content}</p>
      <div className="absolute right-[5%] top-[30%] rounded-full bg-[#f2dec2] w-10 h-10 text-center">
        <p className="mt-2.5 text-sm">{alert > 300 ? '300+' : alert}</p>
      </div>
    </div>
  )
}

export default Chatting