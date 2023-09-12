import React from "react";
import MainStyle from "../components/MainStyle";
import "../styles/HomePageStyle.scss";
import LogoImage from "../assets/logo-mainlogo.svg";

const LandingPage = () => {
  return (
    <MainStyle>
      <div className="loading">
        <img
            src={LogoImage}
            alt="로딩 이미지"
            className="loadingimg"
          />
      </div>
    </MainStyle>
  );
};

export default LandingPage;
