import React from "react";
import "../styles/calendar/CalendarpageStyle.scss"
import CalendarForm from "../components/Calender/CalenderForm";
import MainStyle from "../components/MainStyle";
import ChallengeCounterForm from "../components/Calender/ChallengeCounterForm";
import ChallengeItemsForm from "../components/Calender/ChallengeItemsForm";

const CalendarPage = () => {
  return (
    <MainStyle>
      <div className="calendarpage">
        {/* 캘린더 페이지 헤더 */}
        <div className="calendar_header">
          <h2 className="sub_title">지금까지 모은금액!</h2>
          <h1 className="main_title">{} 원</h1>
        </div>

        {/* 캘린더 */}
        <div className="calendar_form">
          <CalendarForm />
        </div>

        {/* 선택 달 성공 실패 횟수 */}
        <div className="challengecounter_form">
          <ChallengeCounterForm/>
        </div>

        {/* 결제한 아이템 리스트 */}
        <div className="challengeitems_form">
          <ChallengeItemsForm/>
        </div>
      </div>
    </MainStyle>
  );
};

export default CalendarPage;
