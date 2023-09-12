import React, { useState } from "react";
import BubbleChart from "../components/BubbleChart";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";

const RankingPage = () => {
  interface BalanceData {
    balance: number;
  }

  interface DataPoint {
    min: number;
    max: number;
    users: number;
    label: string;
    x?: number;
    y?: number;
  }

  const [selectedUsers, setSelectedUsers] = useState<BalanceData[]>([]);

  const handleBubbleClick = (range: DataPoint) => {
    const dummyBalances: BalanceData[] = [
      { balance: 1500 },
      { balance: 2300 },
      { balance: 5000 },
      { balance: 7500 },
      { balance: 9000 },
      { balance: 1200 },
      { balance: 2400 },
      { balance: 5200 },
      { balance: 5400 },
      { balance: 2300 },
      { balance: 2300 },
      { balance: 2300 },
      { balance: 2300 },
      { balance: 1200 },
      { balance: 1200 },
      { balance: 1200 },
      { balance: 1200 },
      { balance: 1200 },
      { balance: 1200 },
      { balance: 1200 },
      { balance: 1200 },
      { balance: 1200 },
      { balance: 4400 },
      { balance: 8900 },
      { balance: 7500 },
      { balance: 4600 },
      { balance: 5400 },
      { balance: 7700 },
      { balance: 4400 },
      { balance: 1100 },
      { balance: 2200 },
      { balance: 3300 },
      { balance: 4400 },
      { balance: 5500 },
      { balance: 6600 },
      { balance: 7700 },
      { balance: 7700 },
      { balance: 8800 },
      { balance: 8800 },
      { balance: 8800 },
      { balance: 8800 },
      { balance: 8800 },
      { balance: 8800 },
      { balance: 8800 },
      { balance: 9900 },
    ];

    // 선택된 범위 내의 사용자 데이터 필터링
    const usersInRange = dummyBalances.filter(
      (user) => user.balance >= range.min && user.balance <= range.max,
    );
    setSelectedUsers(usersInRange);
  };
  return (
    <CenterContainer>
      <MainStyle>
        <h3>[]명이 도전중!</h3>
        <h1>[]월 []일 챌린지</h1>
        <BubbleChart onBubbleClick={handleBubbleClick} />
      </MainStyle>
    </CenterContainer>
  );
};

export default RankingPage;
