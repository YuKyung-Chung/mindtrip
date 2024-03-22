import React from 'react';
import './MissionTree.css'; // Tree 컴포넌트에 대한 CSS 파일을 임포트합니다.

const Tree: React.FC = () => {
  return (
    <div className="container">
      <div className="tree">
        {[0, 1, 2, 3].map((x) => (
          <div key={x} className="branch" style={{ '--x': x }}>
            {[0, 1, 2, 3].map((i) => (
              <span key={i} style={{ '--i': i }}></span>
            ))}
          </div>
        ))}
        <div className="stem">
          {[0, 1, 2, 3].map((i) => (
            <span key={i} style={{ '--i': i }}></span>
          ))}
        </div>
        <span className="shadow"></span>
      </div>
    </div>
  );
};

export default Tree;
