import React, { useState } from "react";

// style
import "../styles/calendar/CalendarpageStyle.scss"
import MainStyle from "../components/MainStyle";

// component
import CalendarForm from "../components/Calender/CalenderForm";
import ChallengeCounterForm from "../components/Calender/ChallengeCounterForm";
import ChallengeItemsForm from "../components/Calender/ChallengeItemsForm";
import SpentItem from "../components/SpentItem";

//interface value
import { ChallengeItem } from "../constants/interfaces";
import BottomBarSpace from "../components/BottomBarSpace";
import HeaderTitle from "../components/HeaderTitle";

const CalendarPage = () => {

  // const [challengeitems, setChallengeitems] = useState<ChallengeItem[]>([]);
  const [challengeitems, setChallengeitems] = useState<ChallengeItem[]>([
    {
      "category": "카페",
      // "color" : "category_orange_01",
      "content": "메가커피 아메리카노",
      "pay_amount": 2500
      
    },
    {
      "category": "식사",
      // "color" : "category_orange_01",
      "content": "소풍 참치김밥",
      "pay_amount": 5000
    },
    {
      "category": "카페",
      // "color" : "category_orange_01",
      "content": "메가커피 아메리카노",
      "pay_amount": 2500
      
    },
    {
      "category": "식사",
      // "color" : "category_orange_01",
      "content": "소풍 참치김밥",
      "pay_amount": 5000
    },
    {
      "category": "카페",
      // "color" : "category_orange_01",
      "content": "메가커피 아메리카노",
      "pay_amount": 2500
      
    },
    {
      "category": "식사",
      // "color" : "category_orange_01",
      "content": "소풍 참치김밥",
      "pay_amount": 5000
    }
  ]);

  return (
    <MainStyle>
      <div className="calendarpage">
        {/* 캘린더 페이지 헤더 */}
        <HeaderTitle
          SubTitle = "지금까지 모은금액!"
          // MainTitle = {`${} 원`}
          MainTitle = {`25900 원`}
        />
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
              <SpentItem
                name = {item.category}
                mainValue = {item.content}
                moneyValue = {item.pay_amount}
              />
              // <div>{item.category}</div>
            ))
          }
        </div>

        <BottomBarSpace/>
      </div>
    </MainStyle>
  );
};

export default CalendarPage;
