import React from 'react';
import './MissionTree.css'; // Tree 컴포넌트에 대한 CSS 파일을 임포트합니다.

// CSS 변수를 포함한 스타일의 타입을 정의합니다.
type TreeStyle = React.CSSProperties & {
  '--x': number;
  '--i': number;
};

const Tree: React.FC = () => {
  return (
    <div className="container">
      <div className="tree">
        {[0, 1, 2, 3].map((x) => (
          <div key={x} className="branch" style={{ '--x': x } as TreeStyle}>
            {[0, 1, 2, 3].map((i) => (
              <span key={i} style={{ '--i': i } as TreeStyle}></span>
            ))}
          </div>
        ))}
        <div className="stem">
          {[0, 1, 2, 3].map((i) => (
            <span key={i} style={{ '--i': i } as TreeStyle}></span>
          ))}
        </div>
        <span className="shadow"></span>
      </div>
    </div>
  );
};

export default Tree;
