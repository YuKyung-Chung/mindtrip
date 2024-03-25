import React from 'react';
import './postit.css';

interface PostitProps {
  color: string;
  onClick: () => void; // 클릭 이벤트를 받는 속성 추가
}

const Postit: React.FC<PostitProps> = ({ color, onClick, children }) => {
  const postitStyle: React.CSSProperties = {
    backgroundColor: color
  };

  return (
    <div className="rgyPostIt" style={postitStyle} onClick={onClick}> {/* 클릭 이벤트 추가 */}
      <p>{children}</p>
    </div>
  );
};

export default Postit;
