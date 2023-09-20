import React, { useState } from "react";
import DonutChart from "../components/DoughnutChart";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";

// component
import InfoItem from "../components/InfoItem";

//interface value
import { ChallengeItem } from "../constants/interfaces";

const TodayPage = () => {
  // const [costItems, setCostItems] = useState<ChallengeItem[]>([]);
  const [costItems, setCostItems] = useState<ChallengeItem[]>([
    {
      category: "카페",
      content: "메가커피 아메리카노",
      pay_amount: 2500,
    },
    {
      category: "식사",
      content: "소풍 참치김밥",
      pay_amount: 5000,
    },
    {
      category: "카페",
      content: "메가커피 아메리카노",
      pay_amount: 2500,
    },
    {
      category: "식사",
      content: "소풍 참치김밥",
      pay_amount: 5000,
    },
    {
      category: "카페",
      content: "메가커피 아메리카노",
      pay_amount: 2500,
    },
    {
      category: "식사",
      content: "소풍 참치김밥",
      pay_amount: 5000,
    },
    {
      category: "쇼핑",
      content: "변정원 팬티 구매",
      pay_amount: 17000,
    },
    {
      category: "교통",
      content: "101번 버스",
      pay_amount: 2000,
    },
    {
      category: "교통",
      content: "지하철 3호선",
      pay_amount: 2000,
    },
  ]);

  const [category, setCategory] = useState (new Map([]));

  return (
    <CenterContainer>
      <MainStyle>
        <h1>하루 소비 지출 현황</h1>
        {/* api용 */}
        {/* <DonutChart/> */}

        {/* 더미용 */}
        <DonutChart datas={costItems} />
        <div className="challengeitems_list">
          {costItems.map((item, index) => (
            <InfoItem
              icon={item.category}
              mainValue={item.content}
              moneyValue={item.pay_amount}
            />
          ))}
        </div>
      </MainStyle>
    </CenterContainer>
  );
};

export default TodayPage;
