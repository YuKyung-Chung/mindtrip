  import React, { useState, useEffect } from 'react';
  import BackButton from '../../atoms/buttons/backbtn';
  import AlarmButton from '../../atoms/buttons/alambtn';
  import PostIt from '../../atoms/postit/postititem';

  const Postit: React.FC = () => {
    // 포스트잇 색상 배열
    const colors = ['#ffff88', '#ffcc00', '#ff9999', '#99ccff'];

    // 포스트잇 데이터 상태
    const [postits, setPostits] = useState<any[]>([]);

    useEffect(() => {
      // API 호출 등으로 데이터를 받아온다고 가정
      // 받아온 데이터를 상태에 설정
      const fetchedData = [...Array(10)].map(() => ({
        // API에서 받아온 데이터 형식에 따라 조정할 수 있습니다.
        id: Math.random().toString(),
        content: "포스트잇 내용",
        // 랜덤 색상 선택
        color: colors[Math.floor(Math.random() * colors.length)]
      }));
      setPostits(fetchedData);
    }, []);

    return (
      <div className="bg-[#fff7e0] px-2 py-8">
        <div className="flex flex-col justify-center items-center mb-6">
          <BackButton />
          <AlarmButton />
          <h1 className="text-5xl font-bold mt-20">포스트 잇</h1>
        </div>
        <div className="bg-white rounded-lg shadow-md p-6 mb-8 w-4/5 mx-auto">
          <div className="flex justify-center items-center flex-wrap ">
            {postits.map((postit) => (
              <div className="m-2" key={postit.id}>
                <PostIt />
              </div>
            ))}
          </div>
        </div>
        <div className="mt-8 p-2 rounded-lg text-center w-4/5 mx-auto">
          
        </div>
      </div>
    );
  }

  export default Postit;

  // ------------------------------------------------------------

  // API받으면 이거 써보기

  // import React, { useState, useEffect } from 'react';
  // import { Link } from 'react-router-dom';
  // import BackButton from '../../atoms/buttons/backbtn';
  // import AlarmButton from '../../atoms/buttons/alambtn';
  // import PostIt from '../../atoms/postit/postititem';

  // const PostitPage: React.FC = () => {
  //   // 포스트잇 색상 배열
  //   const colors = ['#ffff88', '#ffcc00', '#ff9999', '#99ccff'];

  //   // 포스트잇 데이터 상태
  //   const [postits, setPostits] = useState<any[]>([]);

  //   useEffect(() => {
  //     // API 호출 등으로 데이터를 받아온다고 가정
  //     // 받아온 데이터를 상태에 설정
  //     const fetchData = async () => {
  //       try {
  //         // 데이터를 받아오는 API 호출
  //         const response = await fetch('API_ENDPOINT');
  //         const data = await response.json();

  //         // 받아온 데이터를 상태에 설정
  //         setPostits(data);
  //       } catch (error) {
  //         console.error('Error fetching data:', error);
  //       }
  //     };

  //     fetchData(); // 데이터 가져오기 함수 호출
  //   }, []); // 빈 배열을 넘겨주어 컴포넌트가 마운트될 때만 useEffect가 실행되도록 함

  //   return (
  //     <div className="bg-[#fff7e0] px-2 py-8">
  //       <div className="flex flex-col justify-center items-center mb-6">
  //         <BackButton />
  //         <AlarmButton />
  //         <h1 className="text-5xl font-bold mt-20">포스트 잇</h1>
  //       </div>
  //       <div className="bg-white rounded-lg shadow-md p-6 mb-8 w-4/5 mx-auto">
  //         <div className="flex justify-center items-center flex-wrap ">
  //           {postits.map((postit) => (
  //             <div className="m-2" key={postit.id}>
  //               <PostIt color={colors[Math.floor(Math.random() * colors.length)]}>
  //                 {postit.content}
  //               </PostIt>
  //             </div>
  //           ))}
  //         </div>
  //       </div>
  //       <div className="mt-8 p-2 rounded-lg text-center w-4/5 mx-auto">
  //         <p className="mb-4">오늘의 미션이 맘에 들지 않았다면,</p>
  //         <Link to="/fixmission" className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-700 mt-4">
  //           수정완료버튼
  //         </Link>
  //       </div>
  //     </div>
  //   );
  // }

  // export default PostitPage;
