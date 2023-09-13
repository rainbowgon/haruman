import React, { useState } from "react";
import MainStyle from "../components/MainStyle";
import "../styles/theme.css";
import "../styles/wave.scss";
import { Link } from "react-router-dom";

const Homepage = () => {
  const [challenge, setChallenge] = useState(true);
  const [height, setHeight] = useState(100);

  const handleStartChallenge = () => {
    const nowChallenge = !challenge;
  
    setChallenge(nowChallenge);
  
    let targetHeight: number = nowChallenge ? height - 100 : height + 5;
  
    setTimeout(() => {
      setHeight(targetHeight);
    }, 0);
  };
  
  return (
    <MainStyle>
      <div className="homepage">
        <div className="homepage_header">
          {/* <p className="homepage_text">{}월 {}일</p> */}
          <p className="homepage_text">12월 31일</p>
          {
            challenge
            ?<>
              <div className="homepage_header_title">
                <h2 className="homepage_header_title_text">오늘의 챌린지
                  <div className="style_horizon_line"/>
                </h2>
              </div>
              <p className="homepage_text">{}명의 유저가 먼저 진행하고 있어요!</p>
            </>
            :<>
              <div className="homepage_header_title">
                <h2 className="homepage_header_title_text">13{} : 24{}
                  <div className="style_horizon_line"/>
                </h2>
                
              </div>
              <p className="homepage_text">{}명의 유저와 함께하고 있어요!</p>
            </>
          }
        </div>
        <div>
          <div className="main_circle">
            <div className="start_challenge">
              {
                challenge 
                ?
                <div className="start_challenge_button" onClick={handleStartChallenge}>
                  <h2 className="start_challenge_button_title">챌린지</h2>
                  <h2 className="start_challenge_button_title">시작</h2>
                </div>
                :
                <Link to="/today" className="linkto_today">
                  <div className="progress_challenge">
                    <div className="progress_challenge_title">
                      <h2>8,{height}</h2>
                      <h3>원</h3>
                    </div>
                  </div>
                  <p className="challenge_link_text">지출내역으로 이동</p>
                </Link>
              }
            </div>
            {
              challenge &&
              <div style={{ height: `${height}vw`}}/>
            }
            <div>
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
            <div className="main_circle_gradation"/>
          </div>
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
          <div className="bottom_gradation"/>
        </div>
      </div>
    </MainStyle>
  );
};

export default Homepage;