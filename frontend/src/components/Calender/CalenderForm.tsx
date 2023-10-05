import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import "../../styles/calendar/CalenderForm.scss";
import moment from "moment";
import axios from "axios";
import ChallengeCounterForm from "./ChallengeCounterForm";
import { API_URL } from "../../constants/urls";
import { ChallengeDate, ChallengeState } from "../../constants/interfaces";

interface CalendarFormProp {
  selectChallenge: ChallengeDate | undefined;
  setSelectChallenge: any;
}

const CalendarForm = ({
  selectChallenge,
  setSelectChallenge,
}: CalendarFormProp) => {
  const accessToken = process.env.REACT_APP_accessToken;
  // 배포용
  // const accessToken = localStorage.getItem("accessToken");

  const contextPath = `/api`;
  const challengeAPI = "/challenges";

  const [successCount, setSuccessCount] = useState(0);
  const [failCount, setFailCount] = useState(0);

  const [challengeInfo, setChallengeInfo] = useState<ChallengeState>({
    challengeId: -1,
    participantCount: 0,
    targetAmount: 0,
    usedAmount: 0,
    leftoverAmount: 0,
    challengeStatus: "READY",
  });

  const [selectedChallengeDates, setSelectedChallengeDates] =
    useState("2023-09-01");

  const [challengeDates, setChallengeDates] = useState<ChallengeDate[]>([
    {
      date: "2023-09-01",
      challengeId: 2,
      status: "SUCCESS",
    },
    {
      date: "2023-09-17",
      challengeId: 3,
      status: "FAIL",
    },
  ]);

  useEffect(() => {
    handleChangeMonth(new Date());
    setCurrentDate();
  }, []);

  useEffect(() => {
    var successCount = 0;
    var failCount = 0;

    challengeDates.forEach((challenge) => {
      if (challenge.status === "SUCCESS") {
        successCount++;
      } else if (challenge.status === "FAIL") {
        failCount++;
      }
    });

    setSuccessCount(successCount);
    setFailCount(failCount);
  }, [challengeDates]);

  const setCurrentDate = () => {
    const curDate = new Date();

    const year = `${curDate.getFullYear()}`;
    var month = `${curDate.getMonth() + 1}`;
    if (curDate.getMonth() < 9) {
      month = "0" + (curDate.getMonth() + 1);
    }
    var day = `${curDate.getDate()}`;
    if (curDate.getDate() < 10) {
      day = "0" + curDate.getDate();
    }

    setSelectedChallengeDates(`${year}-${month}-${day}`);
  };

  const handleClickDay = (value: Date) => {
    const year = `${value.getFullYear()}`;
    var month = `${value.getMonth() + 1}`;
    if (value.getMonth() < 9) {
      month = "0" + (value.getMonth() + 1);
    }
    var day = `${value.getDate()}`;
    if (value.getDate() < 10) {
      day = "0" + value.getDate();
    }

    setSelectedChallengeDates(`${year}-${month}-${day}`);

    console.log(challengeDates);
    challengeDates.find((challenge) => {
      console.log(
        challenge,
        challenge.date === `${year}-${month}-${day}`,
        challenge.date,
        `${year}-${month}-${day}`,
      );
      if (challenge.date === `${year}-${month}-${day}`) {
        console.log("challenge.challengeId : ", challenge.challengeId);
        setSelectChallenge(challenge);
      }
    });
  };

  // 월 변경 시 호출할 함수
  const handleChangeMonth = (newDate: Date) => {
    // newDate에는 변경된 날짜 정보가 포함됩니다.
    const year = newDate.getFullYear();
    const month = newDate.getMonth() + 1;

    console.log("월이 변경되었습니다.", `${year}-${month}`);

    axios
      .get(
        `${API_URL}${contextPath}${challengeAPI}/history?date=${year}-${month}`,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        },
      )
      .then((response) => {
        console.log("월 변경 성공", response.data.data);
        setChallengeDates(response.data.data);
      })
      .catch((error) => {
        console.error("월 변경 실패", error);
      });
  };

  const [value, onChange] = useState(new Date());

  return (
    <>
      <div className="mypage_calenders">
        <Calendar
          locale="en"
          value={value}
          //   onChange:String ={onChange}
          onClickMonth={(value) => console.log(value)}
          onClickDay={(value) => handleClickDay(value)}
          tileClassName={({ date }) => {
            const dateString = moment(date).format("YYYY-MM-DD");

            const foundChallenge = challengeDates.find(
              (challenge) => challenge.date === dateString,
            );

            if (foundChallenge) {
              if (foundChallenge.status === "FAIL") {
                return "highlight1";
              } else if (foundChallenge.status === "SUCCESS") {
                return "highlight2";
              } else if (foundChallenge.status === "PROGRESS") {
                return "highlight3";
              }
            } else if (selectedChallengeDates === dateString) {
              return "highlight3";
            }
          }}
          onActiveStartDateChange={({ activeStartDate }) => {
            // 월 변경 시 호출될 함수를 설정합니다.
            if (activeStartDate !== null) {
              handleChangeMonth(activeStartDate);
            }
          }}
        />
      </div>

      {/* 선택 달 성공 실패 횟수 */}
      <div className="challengecounter_form">
        <ChallengeCounterForm
          successCount={successCount}
          failCount={failCount}
        />
      </div>
    </>
  );
};

export default CalendarForm;
