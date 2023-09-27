import React, { useEffect } from "react";
import MainStyle from "../components/MainStyle";
import "../styles/LandingPageStyle.scss";
import LogoImage from "../assets/logo-mainlogo.svg";
import { useNavigate } from "react-router-dom";

const LandingPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => {
      navigate("/login");
    }, 1500);

    return () => clearTimeout(timer);
  }, [navigate]);
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
