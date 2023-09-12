import React from "react";
import DonutChart from "../components/DoughnutChart";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";

const TodayPage = () => {
  return (
    <CenterContainer>
      <MainStyle>
        <h1>하루 소비 지출 현황</h1>
        <DonutChart />
      </MainStyle>
    </CenterContainer>
  );
};

export default TodayPage;
