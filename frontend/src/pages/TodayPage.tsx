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

const TodayPage = () => {
  // const [costItems, setCostItems] = useState<ChallengeItem[]>([]);

  // const [category, setCategory] = useState(new Map([]));
  const [costItems, setCostItems] = useState<ExpenseItem[]>([]);

  const accessToken = process.env.REACT_APP_accessToken;
  const contextPath = `/api`;
  const challengeAPI = "/challenges/24";

  useEffect(() => {
    axios
      .get(`${API_URL}${contextPath}${challengeAPI}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        setCostItems(response.data.data);
        console.log("TodayPage에서 받아오는 것", response.data.data);
      })
      .catch((error) => {
        console.error("Error fetching expense data:", error);
      });
  }, []);

  return (
    <CenterContainer>
      <MainStyle>
        <div className="todaypage">
          <div className="today_header">
            <h2 className="main_title">하루 소비 지출 현황</h2>
          </div>
          {/* api용 */}
          <DonutChart />

          {/* 더미용 */}
          {/* <DonutChart datas={costItems} /> */}
          <div className="challengeitems_list">
            {costItems.map((item, index) => (
              <SpentItem
                name={item.categoryName}
                mainValue={item.content}
                moneyValue={item.payAmount}
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
