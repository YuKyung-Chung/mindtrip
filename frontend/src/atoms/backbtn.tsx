import React from "react";
import { useNavigate } from "react-router-dom";

interface BackButtonProps {}

const BackButton: React.FC<BackButtonProps> = () => {
  const navigate = useNavigate();

  const Backicon: React.FC = () => (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      strokeWidth={3}
      stroke="currentColor"
      className="w-6 h-6 cursor-pointer"
    >
      <path strokeLinecap="round" strokeLinejoin="round" d="M15.75 19.5 8.25 12l7.5-7.5" />
    </svg>
  );

  const goBack = () => {
    navigate(-1);
  };

  return (
    <button
      className="text-black py-2 px-4 rounded hover:bg-gray-300 hover:bg-opacity-25 transition duration-300 transform hover:translate-x-1 hover:translate-y-1 focus:outline-none absolute top-5 left-5"
      onClick={goBack}
    >
      <Backicon />
    </button>
  );
};

export default BackButton;
