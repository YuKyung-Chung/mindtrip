// import React, { useEffect } from 'react';
// import gsap from 'gsap';

// const FixButton = () => {
//   useEffect(() => {
//     const circlesTopLeft = document.querySelectorAll('.circle.top-left');
//     const circlesBottomRight = document.querySelectorAll('.circle.bottom-right');

//     const tl = gsap.timeline();
//     const tl2 = gsap.timeline();

//     const btTl = gsap.timeline({ paused: true });

//     gsap.to(circlesTopLeft, { duration: 1.2, x: -25, y: -25, scaleY: 2, ease: 'power4.inOut' });
//     gsap.to(circlesTopLeft[0], { duration: 0.1, scale: 0.2, x: '+=6', y: '-=2' });
//     gsap.to(circlesTopLeft[1], { scaleX: 1, scaleY: 0.8, x: '-=10', y: '-=7', duration: 0.1, offset: '-=0.1' });
//     gsap.to(circlesTopLeft[2], { scale: 0.2, x: '-=15', y: '+=6', duration: 0.1, offset: '-=0.1' });
//     gsap.to(circlesTopLeft[0], { duration: 1, scale: 0, x: '-=5', y: '-=15', opacity: 0 });
//     gsap.to(circlesTopLeft[1], { scaleX: 0.4, scaleY: 0.4, x: '-=10', y: '-=10', opacity: 0, duration: 1, offset: '-=1' });
//     gsap.to(circlesTopLeft[2], { scale: 0, x: '-=15', y: '+=5', opacity: 0, duration: 1, offset: '-=1' });

//     const tlBt1 = gsap.timeline();
//     const tlBt2 = gsap.timeline();

//     tlBt1.set(circlesTopLeft, { x: 0, y: 0, rotation: -45 });
//     tlBt1.add(tl);

//     tlBt2.set(circlesBottomRight, { x: 0, y: 0 });
//     tlBt2.to(circlesBottomRight, { duration: 1.1, x: 30, y: 30, ease: 'power4.inOut' });
//     tlBt2.to(circlesBottomRight[0], { duration: 0.1, scale: 0.2, x: '-=6', y: '+=3' });
//     tlBt2.to(circlesBottomRight[1], { duration: 0.1, scale: 0.8, x: '+=7', y: '+=3' }, '-=0.1');
//     tlBt2.to(circlesBottomRight[2], { duration: 0.1, scale: 0.2, x: '+=15', y: '-=6' }, '-=0.2');
//     tlBt2.to(circlesBottomRight[0], { duration: 1, scale: 0, x: '+=5', y: '+=15', opacity: 0 });
//     tlBt2.to(circlesBottomRight[1], { duration: 1, scale: 0.4, x: '+=7', y: '+=7', opacity: 0 }, '-=1');
//     tlBt2.to(circlesBottomRight[2], { duration: 1, scale: 0, x: '+=15', y: '-=5', opacity: 0 }, '-=1');

//     tlBt2.set(circlesBottomRight, { x: 0, y: 0, rotation: 45 });
//     tlBt2.add(tl2);

//     btTl.add(tlBt1);
//     btTl.to(document.querySelector('.button.effect-button'), { duration: 0.8, scaleY: 1.1 }, 0.1);
//     btTl.add(tlBt2, 0.2);
//     btTl.to(document.querySelector('.button.effect-button'), { duration: 1.8, scale: 1, ease: 'elastic.out(1.2, 0.4)' }, 1.2);


//     btTl.timeScale(2.6);
//     const buttonBubble = document.querySelector('.button--bubble');
//     const handleMouseOver = () => {
//       btTl.restart();
//     };

//     if (buttonBubble) {
//       buttonBubble.addEventListener('mouseover', handleMouseOver);

//       return () => {
//         buttonBubble.removeEventListener('mouseover', handleMouseOver);
//       };
//     } else {
//       console.error('Button bubble element not found');
//       return () => {};
//     }
//   }, []);

//   return (
//     <div>
//       <svg xmlns="http://www.w3.org/2000/svg" version="1.1" className="goo">
//         <defs>
//           <filter id="goo">
//             <feGaussianBlur in="SourceGraphic" stdDeviation="10" result="blur" />
//             <feColorMatrix in="blur" mode="matrix" values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 19 -9" result="goo" />
//             <feComposite in="SourceGraphic" in2="goo" />
//           </filter>
//         </defs>
//       </svg>

//       <span className="button--bubble__container">
//         <a href="#campaign" className="button button--bubble">
//           Hover me
//         </a>
//         <span className="button--bubble__effect-container">
//           <span className="circle top-left"></span>
//           <span className="circle top-left"></span>
//           <span className="circle top-left"></span>

//           <span className="button effect-button"></span>

//           <span className="circle bottom-right"></span>
//           <span className="circle bottom-right"></span>
//           <span className="circle bottom-right"></span>
//         </span>
//       </span>
//     </div>
//   );
// };

// export default FixButton;
