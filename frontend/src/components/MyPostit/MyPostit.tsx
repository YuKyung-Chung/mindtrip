// import { useState, useRef } from "react";
// import moment from "moment";
import "react-calendar/dist/Calendar.css";
import "./MyPostit.css";

function MyPostit() {

  return (
    <div className="my-postit-container">
      <div className="relative overflow-hidden w-60 h-80 rounded-3xl cursor-pointer text-2xl font-bold bg-purple-400">
        <div className="z-10 absolute w-full h-full peer"></div>
        <div className="absolute peer-hover:-top-20 peer-hover:-left-16 peer-hover:w-[140%] peer-hover:h-[140%] -top-32 -left-16 w-32 h-44 rounded-full bg-purple-300 transition-all duration-500"></div>
        <div className="absolute flex text-xl text-center items-end justify-end peer-hover:right-0 peer-hover:rounded-b-none peer-hover:bottom-0 peer-hover:items-center peer-hover:justify-center peer-hover:w-full peer-hover:h-full -bottom-32 -right-16 w-36 h-44 rounded-full bg-purple-300 transition-all duration-500">
          Nice to meet u,
          <br />
          Uiverse
        </div>
        <div className="w-full h-full items-center justify-center flex uppercase">
          hover me
        </div>
      </div>
    </div>
  );
}

export default MyPostit;
