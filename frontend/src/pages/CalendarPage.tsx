import React from "react";
import CalendarForm from "../components/Calender/CalenderForm";
import MainStyle from "../components/MainStyle";

const CalendarPage = () => {
  return (
    <MainStyle>
      <div className="calendar_header">
        <h2 className="sub_title">지금까지 모은금액!</h2>
        <h1 className="main_title">{} 원</h1>
      </div>
      <div className="calendar_form">
        <CalendarForm />
      </div>
      <div>
        {/* 선택 달 성공 실패 횟수 */}
      </div>
      <div>
        {/* 결제한 아이템 리스트 */}
      </div>
    </MainStyle>
  );
};

export default CalendarPage;
