import { useNavigate } from "react-router-dom"
import HomeIcon from "../Icons/HomeIcon";

const Homebtn = function() {
  const navigate = useNavigate();

  const goBack = () => {
    navigate('/main');
  };

  return (
    <button
      className="text-black py-2 px-4 rounded hover:bg-gray-300 hover:bg-opacity-25 transition duration-300 transform hover:translate-x-1 hover:translate-y-1 focus:outline-none absolute top-5 left-5"
      onClick={goBack}
    >
      <HomeIcon />
    </button>
  );
};

export default Homebtn;