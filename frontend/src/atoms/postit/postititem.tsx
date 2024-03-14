import React from 'react';
import './postit.css';

const Postit: React.FC = () => {
  // 랜덤 색상 배열
  const colors = ['#ffff88', '#ffcc00', '#ff9999', '#99ccff'];

  // 랜덤 색상 선택
  const randomColor = colors[Math.floor(Math.random() * colors.length)];

  // 스타일 객체 생성
  const postitStyle: React.CSSProperties = {
    backgroundColor: randomColor
  };

  return (
    <div className="rgyPostIt" style={postitStyle}>
      <p>방가</p>
    </div>
  );
};

export default Postit;
