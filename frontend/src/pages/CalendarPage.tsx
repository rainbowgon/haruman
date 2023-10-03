import React, { useEffect, useState } from "react";
import axios from "axios";

// style
import "../styles/calendar/CalendarpageStyle.scss"
import MainStyle from "../components/MainStyle";

// urls
import { API_URL } from "../constants/urls";

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
  // 테스트용
  const accessToken = process.env.REACT_APP_accessToken;
  // 배포용
  // const accessToken = sessionStorage.getItem("accessToken");

  const contextPath = `/api`;
  const ChallengeAPI = '/challenges';

  const [selectChallengeId, setSelectChallengeId] = useState<number | null>(null);
  const [amount, setAmount] = useState(0);

  // const [challengeitems, setChallengeitems] = useState<ChallengeItem[]>([]);
  const [challengeitems, setChallengeitems] = useState<ChallengeItem[]>([
    {
      "category": "카페",
      // "color" : "category_orange_01",
      "content": "메가커피 아메리카노",
      "pay_amount": 2500
      // "payAmount": 2500
    },
    {
      "category": "식사",
      // "color" : "category_orange_01",
      "content": "소풍 참치김밥",
      "pay_amount": 5000
      // "payAmount": 5000
    },
    {
      "category": "카페",
      // "color" : "category_orange_01",
      "content": "메가커피 아메리카노",
      "pay_amount": 2500
      // "payAmount": 2500
      
    },
    {
      "category": "식사",
      // "color" : "category_orange_01",
      "content": "소풍 참치김밥",
      "pay_amount": 5000
      // "payAmount": 5000
    },
    {
      "category": "카페",
      // "color" : "category_orange_01",
      "content": "메가커피 아메리카노",
      "pay_amount": 2500
      // "payAmount": 2500
    },
    {
      "category": "식사",
      // "color" : "category_orange_01",
      "content": "소풍 참치김밥",
      "pay_amount": 5000
      // "payAmount": 5000
    }
  ]);

  useEffect(() => {
    selectAccumulatedAmount();
  }, [])

  useEffect(() => {
    selectDailyChallenge();
  }, [selectChallengeId])



  //selectAccumulatedAmount
  //get
  const selectAccumulatedAmount = () => {
    axios.get(`${API_URL}${contextPath}${ChallengeAPI}/amount`,
      {
        headers: {
          Authorization: `Bearer ${accessToken}`
        }
      })
      .then((response) => {
        console.log("누적 잔액 조회 성공", response.data.data);
        setAmount(response.data.data);
      })
      .catch((error) => {
        console.error('누적 잔액 조회 실패', error);
      });
  }

  /**
    selectDailyChallenge
    해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트)
    /challenges?date={date}   
    GET
  */
    const selectDailyChallenge = () => {
      console.log("selectDailyExpenseList");
      
      console.log("selectChallengeId : " , selectChallengeId);
      if (selectChallengeId === null) {
        setChallengeitems([]);
        return;
      }
  
      axios.get(`${API_URL}${contextPath}${ChallengeAPI}/${selectChallengeId}`,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`
          }
        })
        .then((response) => {
          console.log("해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트)", response.data.data);
          console.log(response.data.data);
          setChallengeitems(response.data.data);
          // setChallengeInfo(response.data.data);
        })
        .catch((error) => {
          console.error('해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트) 조회 실패', error);
        });
    };

  return (
    <MainStyle>
      <div className="calendarpage">
        {/* 캘린더 페이지 헤더 */}
        <HeaderTitle
          SubTitle = "지금까지 모은금액!"
          MainTitle = {`${amount} 원`}
          // MainTitle = {`25900 원`}
        />
        {/* 캘린더 */}
        <div className="calendar_form">
          <CalendarForm 
            selectChallengeId = {selectChallengeId}
            setSelectChallengeId = {setSelectChallengeId}
          />
        </div>

        {/* 결제한 아이템 리스트 */}
        <div className="challengeitems_list">
          {
            challengeitems && challengeitems.length !== 0 
            ?
            challengeitems.map((item) => (
              <SpentItem
                name = {item.category}
                mainValue = {item.content}
                moneyValue = {item.pay_amount}
              />
            ))
              // <div className="challengeitems_emptylist">
              //   1이 날은 소비를 하지 않았어요!
              //   {challengeitems.length}
              // </div>
            :
            <div className="challengeitems_emptylist">
              이 날은 소비를 하지 않았어요!
            </div>
          }
        </div>

        <BottomBarSpace/>
      </div>
    </MainStyle>
  );
};

export default CalendarPage;
