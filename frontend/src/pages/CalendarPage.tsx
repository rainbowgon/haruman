import React, { useState } from "react";

// style
import "../styles/calendar/CalendarpageStyle.scss"
import MainStyle from "../components/MainStyle";

// component
import CalendarForm from "../components/Calender/CalenderForm";
import ChallengeCounterForm from "../components/Calender/ChallengeCounterForm";
import ChallengeItemsForm from "../components/Calender/ChallengeItemsForm";
import InfoItem from "../components/InfoItem";

//interface value
import { ChallengeItem } from "../constants/interfaces";

const CalendarPage = () => {

  // const [challengeitems, setChallengeitems] = useState<ChallengeItem[]>([]);
  const [challengeitems, setChallengeitems] = useState<ChallengeItem[]>([
    {
      "category": "카페",
      "content": "메가커피 아메리카노",
      "pay_amount": 2500
      
    },
    {
      "category": "식사",
      "content": "소풍 참치김밥",
      "pay_amount": 5000
    },
    {
      "category": "카페",
      "content": "메가커피 아메리카노",
      "pay_amount": 2500
      
    },
    {
      "category": "식사",
      "content": "소풍 참치김밥",
      "pay_amount": 5000
    },
    {
      "category": "카페",
      "content": "메가커피 아메리카노",
      "pay_amount": 2500
      
    },
    {
      "category": "식사",
      "content": "소풍 참치김밥",
      "pay_amount": 5000
    }
  ]);

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
        <div className="challengeitems_list">
          {
            challengeitems.map((item, index) => (
              <InfoItem
                icon = {item.category}
                mainValue = {item.content}
                moneyValue = {item.pay_amount}
              />
              // <div>{item.category}</div>
            ))
          }
        </div>

        <div className="bottom_bar_spacer">
        </div>
      </div>
    </MainStyle>
  );
};

export default CalendarPage;
