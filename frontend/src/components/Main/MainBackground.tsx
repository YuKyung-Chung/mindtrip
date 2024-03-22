// import * as THREE from 'three'
// import { useRef, useEffect } from 'react'
// import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js'

// function MainBackground() {
//   // 3d 모델 우선 띄우기
//   const canvasRef = useRef<HTMLCanvasElement>(null);
//   const loader = new GLTFLoader()

//   // 배경
//   const scene = new THREE.Scene()

//   // 카메라
//   const camera = new THREE.PerspectiveCamera(15, window.innerWidth / window.innerHeight, 0.1, 1000)
//   // camera.position.set(-30,4,0)
//   // camera.rotation.y -= 1.5
//   camera.position.set(-1.5,6,30)
//   camera.rotation.x -= 0.1

//   useEffect(() => {

//     if (canvasRef.current) { 
//       let renderer = new THREE.WebGLRenderer({ canvas: canvasRef.current })
//       // 캔버스 크기
//       renderer.setSize(window.innerWidth, window.innerHeight)
//       // 배경(투명하게 설정)
//       renderer.setClearColor(0x000000, 0);
//       // 모델 불러오기
//       loader.load('/village.glb', function (gltf: any) {
//         // 조명
//         const directionalLight = new THREE.DirectionalLight(0xffffff, 4); // color, intensity
//         directionalLight.position.set(-5, 3, 1); // x, y, z
//         scene.add(directionalLight);
//         // 정면 볼 수 있게 돌리기
//         gltf.scene.rotation.y -= 1.5
//         scene.add(gltf.scene)
//         renderer.render(scene, camera)
//       }, undefined, function (err: any) {
//         console.log(err)
//       })
//     }
//   }, [])

//   return (
//       <div className='bg-sky-100'>
//         <canvas ref={canvasRef}></canvas>
//       </div>
//   )
// }

// export default MainBackground