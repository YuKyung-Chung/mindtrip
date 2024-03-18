import React, { useState } from 'react';
import Calendar from "../../../components/Calendar/Calendar"

function MypageHistory() {
  const [selectedDate, setSelectedDate] = useState<Date | null>(new Date());

  return (
    <div className="bg-[#f4c2c2] flex justify-center flex-col align-items items-center min-h-screen relative">

      <div>
        <Calendar selectedDate={selectedDate} setSelectedDate={setSelectedDate} />
      </div>
    </div>
  );
}
export default MypageHistory