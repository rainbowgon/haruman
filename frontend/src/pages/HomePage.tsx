import React, { useState } from "react";
import MainStyle from "../components/MainStyle";
import "../styles/theme.css";
import "../styles/wave.css";
import { Link } from "react-router-dom";

const Homepage = () => {
  const [nowChallenge, setNowChallenge] = useState(true);

  return (
    <MainStyle>
      <div className="homepage_header">
        <p>{}월 {}일</p>
        {
          nowChallenge
          ?<>
            <div>
              <h1>오늘의 도전을 시작하세요!</h1>
            </div>
            <p>{}명의 유저가 먼저 진행하고 있어요!</p>
          </>
          :<>
            <div>
              <h1>{} : {}</h1>
            </div>
            <p>{}명의 유저와 함께하고 있어요!</p>
          </>
        }
      </div>
      <div>
        <div className="header">
          <div>
            <div className="hei"/>
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
          <div className="color"/>
        </div>
        <Link to="/today">today로 이동 </Link>
      </div>
        <div className="getspace"/>
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
        <div className="color"/>
      </div>
    </MainStyle>
  );
};

export default Homepage;