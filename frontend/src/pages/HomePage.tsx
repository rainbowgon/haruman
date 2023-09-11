import React from "react";
import MainStyle from "../components/MainStyle";
import "../styles/theme.css";
import "../styles/wave.css";

const Homepage = () => {
  return (
    <MainStyle>
      <h1>HOMEPAGE (메인 동그라미 있고 한 페이지) 입니다.</h1>
      <div className="header">
      <div>
        <div className="hei">
        </div>
        <svg className="waves" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
        <defs>
          <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
        </defs>
        <g className="parallax">
          <use xlinkHref="#gentle-wave" x="48" y="0" fill="rgba(93,215,190,0.7)" />
          <use xlinkHref="#gentle-wave" x="48" y="5" fill="var(--brand2_op_40)" />
          <use xlinkHref="#gentle-wave" x="48" y="3" fill="rgba(93,215,190,0.3)" />
          <use xlinkHref="#gentle-wave" x="48" y="5" fill="var(--gradation-start)" />
        </g>
      </svg>
      </div>
      <div className="color">
      </div>
      </div>
        <div className="getspace">
        </div>
        <div>
          <svg className="waves" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
            <defs>
              <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
            </defs>
            <g className="parallax">
            <use xlinkHref="#gentle-wave" x="48" y="0" fill="rgba(93,215,190,0.7" />
            <use xlinkHref="#gentle-wave" x="48" y="3" fill="var(--brand2_op_40)" />
            <use xlinkHref="#gentle-wave" x="48" y="5" fill="rgba(93,215,190,0.3)" />
            <use xlinkHref="#gentle-wave" x="48" y="8" fill="var(--gradation-start)" />
            </g>
          </svg>
        <div className="color">
        </div>
      </div>
    </MainStyle>
  );
};

export default Homepage;
