import React, { useEffect, useState } from "react";
import axios from "axios";

// style
import "../styles/calendar/CalendarpageStyle.scss";
import MainStyle from "../components/MainStyle";

// urls
import { API_URL } from "../constants/urls";

// component
import CalendarForm from "../components/Calender/CalenderForm";
import ChallengeCounterForm from "../components/Calender/ChallengeCounterForm";
import ChallengeItemsForm from "../components/Calender/ChallengeItemsForm";
import SpentItem from "../components/SpentItem";

//interface value
import { ChallengeDate, ChallengeItem } from "../constants/interfaces";
import BottomBarSpace from "../components/BottomBarSpace";
import HeaderTitle from "../components/HeaderTitle";

const CalendarPage = () => {
  // 배포용
  const accessToken = localStorage.getItem("accessToken");

  const contextPath = `/api`;
  const ChallengeAPI = "/challenges";

  const [selectChallenge, setSelectChallenge] = useState<ChallengeDate>();
  const [amount, setAmount] = useState(0);
  const [dailyAmount, setDailyAmount] = useState(0);

  const [challengeitems, setChallengeitems] = useState<ChallengeItem[]>([]);

  useEffect(() => {
    selectAccumulatedAmount();
  }, []);

  useEffect(() => {
    selectDailyChallenge();
  }, [selectChallenge]);

  useEffect(() => {
    var sumAmount = 0;

    challengeitems.forEach((items) => {
      sumAmount += items.payAmount;
    });

    setDailyAmount(sumAmount);
  }, [challengeitems]);

  /**
   * selectAccumulatedAmount
   * 회원의 챌린지 누적 잔액 조회
   * /challenges/amount
   * GET
   */
  const selectAccumulatedAmount = () => {
    axios
      .get(`${API_URL}${contextPath}${ChallengeAPI}/amount`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        console.log(
          "누적 잔액 조회 성공",
          response.data.data.accumulatedAmount,
        );
        setAmount(response.data.data.accumulatedAmount);
      })
      .catch((error) => {
        console.error("누적 잔액 조회 실패", error);
      });
  };

  /**
    selectDailyChallenge
    해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트)
    /challenges?date={date}   
    GET
  */
  const selectDailyChallenge = () => {
    console.log("selectDailyExpenseList");

    console.log("selectChallengeId : ", selectChallenge?.challengeId);
    if (selectChallenge === null) {
      setChallengeitems([]);
      return;
    }

    axios
      .get(
        `${API_URL}${contextPath}${ChallengeAPI}/${selectChallenge?.challengeId}`,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        },
      )
      .then((response) => {
        console.log(
          "해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트)",
          response.data.data,
        );
        console.log(response.data.data);
        setChallengeitems(response.data.data);
      })
      .catch((error) => {
        console.error(
          "해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트) 조회 실패",
          error,
        );
      });
  };

  const numberFormatter = (value: number) => {
    if (value){
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  };

  return (
    <MainStyle>
      <div className="calendarpage">
        {/* 캘린더 페이지 헤더 */}
        <HeaderTitle
          SubTitle="지금까지 모은금액!"
          MainTitle={`${amount && numberFormatter(amount)} 원`}
        />
        {/* 캘린더 */}
        <div className="calendar_form">
          <CalendarForm
            selectChallenge={selectChallenge}
            setSelectChallenge={setSelectChallenge}
          />
        </div>

        {/* 결제한 아이템 리스트 */}
        <div className="challengeitems_form">
          <ChallengeItemsForm
            selectChallenge={selectChallenge}
            dailyAmount={dailyAmount}
          />
        </div>

        {/* 결제한 아이템 리스트 */}
        <div className="challengeitems_list">
          {challengeitems && challengeitems.length !== 0 ? (
            challengeitems.map((item) => (
              <SpentItem
                name={item.categoryName}
                color={item.categoryColor}
                mainValue={item.content}
                moneyValue={item.payAmount}
                id={item.id}
              />
            ))
          ) : (
            <div className="challengeitems_emptylist">
              이 날은 소비를 하지 않았어요!
            </div>
          )}
        </div>

        <BottomBarSpace />
      </div>
    </MainStyle>
  );
};

export default CalendarPage;
