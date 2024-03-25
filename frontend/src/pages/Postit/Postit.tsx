import React, { useState, useEffect } from 'react';
import BackButton from '../../atoms/buttons/backbtn';
import AlarmButton from '../../atoms/buttons/alambtn';
import PostIt from '../../atoms/postit/postititem';
import PostitModal from '../../components/MyPostit/PostitModal'; // 모달 컴포넌트를 import 해주세요
import axios from 'axios';

const PostitPage: React.FC = () => {
  const colors = ['#ffff88', '#ffcc00', '#ff9999', '#99ccff'];
  const [postits, setPostits] = useState<any[]>([]);
  const [topic, setTopic] = useState<string>("");
  const [topicId, setTopicId] = useState<string>("");
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("https://mindtrip.site/api/postits/v1?date=2024-03-21&order=like&village=1", {
          headers: {
            "x-member-id": "4"
          }
        });
        setPostits(response.data.result.postitResList);
        setTopic(response.data.result.topic);
        setTopicId(response.data.result.topicId);
      } catch (error) {
        console.log("Error fetching missions:", error);
      }
    };
    fetchData(); 
  }, []);

  const addPostit = async (content: string) => {
    try {
      const response = await axios.post(
        "https://mindtrip.site/api/postits/v1",
        {
          topicId: topicId,
          postitDate: "2024-03-21",
          content: content
        },
        {
          headers: {
            "x-member-id": "4"
          }
        }
      );
      console.log("새 포스트잇:", response.data);
      setIsModalOpen(false); // 새로운 포스트잇을 추가한 후에 모달을 닫습니다.
      fetchData(); // 모달을 닫은 후에 포스트잇 목록을 다시 불러옵니다.
    } catch (error) {
      console.log("새 포스트잇 에러 :", error);
    }
  };

  const handleFirstPostitClick = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const handleNewPostSubmit = (content: string) => {
    addPostit(content);
  };

  return (
    <div className="bg-[#fff7e0] px-2 py-8">
      <div className="flex flex-col justify-center items-center mb-6">
        <BackButton />
        <AlarmButton />
        <h1 className="text-xl font-bold mt-20">{topic}</h1>
      </div>
      <div className="bg-white rounded-lg shadow-md p-6 mb-8 w-4/5 mx-auto">
        <div className="flex justify-center items-center flex-wrap ">
          <div className="m-2">
            <PostIt
              color={colors[Math.floor(Math.random() * colors.length)]}
              onClick={handleFirstPostitClick}
              style={{ transition: "transform 0.3s ease-in-out" }}
            >
              추가하기
            </PostIt>
          </div>
          {postits.map((postit) => (
            <div className="m-2" key={postit.id}>
              <PostIt color={postit.color}>
                {postit.content}
              </PostIt>
            </div>
          ))}
        </div>
      </div>
      <div className="mt-8 p-2 rounded-lg text-center w-4/5 mx-auto">
      </div>
      <PostitModal
        isOpen={isModalOpen}
        onClose={handleCloseModal}
        onSubmit={handleNewPostSubmit}
      />
    </div>
  );
};

export default PostitPage;
