// // HTPDrawing.tsx
// import React from 'react';

// const HTPDrawing: React.FC = () => {
//   return (
//     <div className="mycard">
//       <div className="bg"></div>
//       <div className="blob"></div>
//     </div>
//   );
// }

// export default HTPDrawing;

// HTPDrawing.tsx
// HTPDrawing.tsx
import React from 'react';

interface HTPDrawingProps {
  imageUrl: string;
}

const HTPDrawing: React.FC<HTPDrawingProps> = ({ imageUrl }) => {
  return (
    <div className="mycard">
      <div className="bg">
        <img src={imageUrl} alt="Drawing" style={{ width: '100%', height: '100%' }} />
      </div>
      <div className="blob"></div>
    </div>
  );
}

export default HTPDrawing;

