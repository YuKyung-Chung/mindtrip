import { useRef, useState, useEffect, useCallback } from 'react'
import { Button } from '@nextui-org/react';

function Draw() {
  const canvasRef = useRef<HTMLCanvasElement>(null)
  const [isDrawing, setIsDrawing] = useState(false)


  // 그림 그리기 시작
  const startDrawing = useCallback((e: MouseEvent): void => {
    const canvas = canvasRef.current;
    if (!canvas) return;
    const ctx = canvas.getContext('2d');
    if (!ctx) return;
    ctx.beginPath();
    // MouseEvent의 offsetX, offsetY를 사용하기 위해 타입 단언
    ctx.moveTo((e as MouseEvent).offsetX, (e as MouseEvent).offsetY);
    setIsDrawing(true);
  }, []);


  // 그려줌
  const draw = useCallback((e: MouseEvent) => {
    if (!isDrawing) return;
    const canvas = canvasRef.current;
    if (!canvas) return;
    const ctx = canvas.getContext('2d');
    if (!ctx) return;
    // MouseEvent의 offsetX, offsetY를 사용하기 위해 타입 단언
    ctx.lineTo((e as MouseEvent).offsetX, (e as MouseEvent).offsetY);
    ctx.stroke();
  }, [isDrawing]);

  // 그만 그리면 멈춰주는 함수
  const stopDrawing = useCallback(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;
    const ctx = canvas.getContext('2d');
    if (ctx) {
      ctx.closePath();
    }
    setIsDrawing(false);
  }, []);

  // 그림 초기화 함수
  const clearCanvas = () => {
    const canvas = canvasRef.current;
    const ctx = canvas?.getContext('2d');
    if (ctx && canvas) {
      ctx.clearRect(0, 0, canvas.width, canvas?.height)
    }
  }

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    if (canvas.width !== canvas.offsetWidth || canvas.height !== canvas.offsetHeight) {
      canvas.width = canvas.offsetWidth;
      canvas.height = canvas.offsetHeight;
    }
    // 이벤트 리스너 추가 및 제거 함수에서 타입 캐스팅
    const handleEvent = (event: string, listener: (e: MouseEvent) => void, method: 'add' | 'remove') => {
      canvas[`${method}EventListener`](event, listener as EventListener);
    };

    // 이벤트 리스너 추가
    handleEvent('mousedown', startDrawing, 'add');
    handleEvent('mousemove', draw, 'add');
    handleEvent('mouseup', stopDrawing, 'add');
    handleEvent('mouseout', stopDrawing, 'add');

    return () => {
      // 이벤트 리스너 제거
      handleEvent('mousedown', startDrawing, 'remove');
      handleEvent('mousemove', draw, 'remove');
      handleEvent('mouseup', stopDrawing, 'remove');
      handleEvent('mouseout', stopDrawing, 'remove');
    };
  }, [startDrawing, draw, stopDrawing]);

  return (
    <div className="relative w-full h-full">
      <canvas ref={canvasRef} className="w-full h-full cursor-crosshair" />
      <Button 
        onClick={clearCanvas} 
        className='absolute top-3 left-3'
        size='sm'
        variant='light'
        color='secondary'
        endContent={
          <TrashIcon/>
        }
      >초기화</Button>
    </div>
  )
}

export default Draw

function TrashIcon() {
  return (
    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
      <path strokeLinecap="round" strokeLinejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
    </svg>
  )
}