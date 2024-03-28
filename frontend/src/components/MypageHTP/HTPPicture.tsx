// HTPPicture.tsx
import React from 'react';
import HTPDrawing from './HTPDrawing';
import testimg from '../../assets/mococo.jpg'; // 임시 이미지 경로

interface HTPPictureProps {
  title: string;
}

const HTPPicture: React.FC<HTPPictureProps> = ({ title }) => {
  return (
    <div>
      <div className='mt-6'>{title}</div> {/* title props를 표시 */}
      <div className="flex space-x-4 overflow-x-scroll">
        <HTPDrawing imageUrl={testimg} />
        <HTPDrawing imageUrl={testimg} />
        <HTPDrawing imageUrl={testimg} />
      </div>
    </div>
  );
};

export default HTPPicture;

