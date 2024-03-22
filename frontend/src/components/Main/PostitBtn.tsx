import * as THREE from 'three'
import { useRef, useEffect, useState } from 'react'
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js'
import { useNavigate } from 'react-router-dom';
import { Tooltip } from "@nextui-org/react";

function PostitBtn() {
  const navigate = useNavigate()
  const [enter, setEnter] = useState(false)

  // 3d 모델 우선 띄우기
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const loader = new GLTFLoader()
  // 배경
  const scene = new THREE.Scene()
  // scene.background = new THREE.Color(0xE2E8F0)
  // 카메라
  const camera = new THREE.PerspectiveCamera(30, 1)
  const mouseEnter = function () {
    setEnter(true)
  }
  const mouseLeave = function () {
    setEnter(false)
  }

  // const animationFrameId: any = useRef();

  useEffect(() => {
    if (canvasRef.current) {
      let renderer = new THREE.WebGLRenderer({ canvas: canvasRef.current })
      // 캔버스 크기
      renderer.setSize(250, 250)
      // 배경(투명하게 설정)
      renderer.setClearColor(0x000000, 0);
      // 모델 불러오기
      loader.load('/board.glb', function (gltf: any) {
        // 조명
        const directionalLight = new THREE.DirectionalLight(0xffffff, 4); // color, intensity
        directionalLight.position.set(0, 1, 2); // x, y, z
        scene.add(directionalLight);
        // 정면 볼 수 있게 돌리기
        gltf.scene.rotation.y -= 3.2
        scene.add(gltf.scene)
        renderer.render(scene, camera)

        // 호버했을 때 돌려보자
        // function animate() {
        //   animationFrameId.current = requestAnimationFrame(animate);
        //   gltf.scene.rotation.y -= 0.01
        //   renderer.render(scene, camera);
        // }
        // if (enter) {
        //   camera.position.set(0, 1, 6)
        //   animate()
        // } else {
        //   if (animationFrameId.current) {
        //     cancelAnimationFrame(animationFrameId.current);
        //   }
        //   camera.position.set(0, 1, 6)
        //   renderer.render(scene, camera)
        // }
        camera.position.set(0, 1, 6)
        renderer.render(scene, camera)
      }, undefined, function (err: any) {
        console.log(err)
      })
    }
  }, [enter])

  return (
    <Tooltip showArrow={true} content="포스트잇 붙이러가기" className='text-lg font-bold'>
      <div onMouseEnter={mouseEnter} onMouseLeave={mouseLeave} className='w-[250px] h-[230px] hover:cursor-pointer'>
        <canvas ref={canvasRef} onClick={() => { navigate('/postit') }}></canvas>
      </div>
    </Tooltip>
  )
}

export default PostitBtn