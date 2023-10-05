import React, { useState, useEffect } from "react";
// 더미용
// import DonutChart from "../components/DoughnutChart";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";
// api용
import DonutChart, { ExpenseItem } from "../components/DoughnutChartApi";

// styles
import "../styles/calendar/CalendarpageStyle.scss";
import "../styles/TodayPageStyle.scss";

// component
import SpentItem from "../components/SpentItem";

//interface value
import { CategoryItem, ChallengeItem } from "../constants/interfaces";
import axios from "axios";
import { API_URL } from "../constants/urls";
import HeaderTitle from "../components/HeaderTitle";
import MiddleTitle from "../components/MiddleTitle";

const TodayPage = () => {
  const [challengeId, setChallengeId] = useState(null);
  // const [costItems, setCostItems] = useState<ChallengeItem[]>([]);

  // const [category, setCategory] = useState(new Map([]));
  const [costItems, setCostItems] = useState<ExpenseItem[]>([]);

  
  const accessToken = process.env.REACT_APP_accessToken;
  // 배포용
  // const accessToken = localStorage.getItem("accessToken");
  useEffect(() => {
    axios
      .get(`${API_URL}/api/challenges`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        const receivedChallengeId = response.data.data.challengeId;
        setChallengeId(receivedChallengeId);
      })
      .catch((error) => {
        console.error("Error fetching expense data:", error);
      });
  }, []);

  const contextPath = `/api`;

  useEffect(() => {
    if (challengeId) {
      const challengeAPI = `/challenges/${challengeId}`;

      axios
        .get(`${API_URL}${contextPath}${challengeAPI}`, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        })
        .then((response) => {
          setCostItems(response.data.data);
        })
        .catch((error) => {
          console.error("Error fetching expense data:", error);
        });
    }
  }, [challengeId]);

  return (
    <CenterContainer>
      <MainStyle>
        <div className="todaypage">
          <HeaderTitle
            SubTitle={``}
            MainTitle={`하루 소비 지출 현황`}
          />
          {/* api용 */}
          <div className="chart-container">
            <DonutChart />
          </div>
          {/* 더미용 */}
          {/* <DonutChart datas={costItems} /> */}
          <div className="challengeitems_list">
            {costItems.map((item, index) => (
              <SpentItem
                name={item.categoryName}
                mainValue={item.content}
                moneyValue={item.payAmount}
                color={item.categoryColor}
              />
            ))}
          </div>
          <div className="bottom_bar_spacer"></div>
        </div>
      </MainStyle>
    </CenterContainer>
  );
};

export default TodayPage;
