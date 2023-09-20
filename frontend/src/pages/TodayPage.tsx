import React, {useState} from "react";
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
    <CenterContainer>
      <MainStyle>
        <h1>하루 소비 지출 현황</h1>
        <DonutChart />

        <div className="challengeitems_list">
          {
            costItems.map((item, index) => (
              <InfoItem
                icon = {item.category}
                mainValue = {item.content}
                moneyValue = {item.pay_amount}
              />
            ))
          }
        </div>
      </MainStyle>
    </CenterContainer>
  );
};

export default TodayPage;
