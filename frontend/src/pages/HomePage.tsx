import React, { useState, useEffect, useRef } from "react";
import { API_URL } from "../constants/urls";
import { Link, useNavigate } from "react-router-dom";
import Swal from "sweetalert2";

// style
import MainStyle from "../components/MainStyle";
import "../styles/theme.css";
import "../styles/wave.scss";
import "../styles/LandingPageStyle.scss";

// image
import Logo from "../assets/logo-mainlogo.svg";

// components
import RegisterButton from "../components/RegistButton";
import RegistModal from "../components/RegistModal";
import { ChallengeState } from "../constants/interfaces";
import axios from "axios";

const Homepage = () => {
  // 배포용
  // const accessToken = localStorage.getItem("accessToken");
  const accessToken =
    "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJfaWQiOiIzNmMwZmNkMC0wMTI1LTQzMmYtYWMwOS1kOTYwNDVjZjdhYjMiLCJpYXQiOjE2OTY1MjkwMzUsImV4cCI6MTY5NjUzMjYzNX0.hwCbjxYBY2o0wgFKSQOBvDzmiAuR_XZGY1THV9eVP8I";

  const navigate = useNavigate();

  const [isLoading, setIsLoading] = useState(true);

  const contextPath = `/api`;
  const ChallengeAPI = "/challenges";
  const canStart: number[] = [5, 12];

  const [currentDate, setCurrentDate] = useState(new Date());
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalTop, setModalTop] = useState(100);
  const [waveTop, setWaveTop] = useState(100);
  const [challenge, setChallenge] = useState(false);
  const [amountPercent, setAmountPercent] = useState(100);
  const [challengeInfo, setChallengeInfo] = useState<ChallengeState>({
    challengeId: -1,
    participantCount: 0,
    targetAmount: 0,
    usedAmount: 0,
    leftoverAmount: 0,
    challengeStatus: "READY",
  });

  // 매 초마다 시간 재설정
  useEffect(() => {
    selectDailyChallenge();

    const intervalId = setInterval(() => {
      setCurrentDate(new Date());
    }, 1000);

    return () => clearInterval(intervalId);
  }, []);

  useEffect(() => {
    let curWave: number = waveTop;
    let targetWave: number = 0;

    function frame() {
      curWave = lerp(curWave, targetWave, 0.02);

      setWaveTop(curWave);

      if (targetWave - curWave < 0) {
        setTimeout(frame, 20);
      } else {
        setWaveTop(targetWave);
      }
    }
    requestAnimationFrame(frame);

    function lerp(s: number, e: number, a: number) {
      return s + (0 - s) * a;
    }
  }, []);

  useEffect(() => {
    let targetModalTop: number = isModalOpen ? 25 : 100;
    if (isModalOpen) {
      setModalTop(targetModalTop);
    } else {
      setModalTop(targetModalTop);
    }
  }, [isModalOpen]);

  const handleStartChallenge = () => {
    // 테스트 끝나면 open

    if (currentDate.getHours() < canStart[0]) {
      showAlert(`${canStart[0]}시에 시작할 수 있습니다.`);
      // return;
    }
    if (currentDate.getHours() >= canStart[1]) {
      showAlert(`시작 가능한 시간이 지났습니다.`);
      // return;
    }

    startChallenge();
  };

  // 챌린지 관련
  /**
    startChallenge   
    챌린지 시작   
    /challenges   
    POST
  */

  const startChallenge = () => {
    axios
      .post(`${API_URL}${contextPath}${ChallengeAPI}`, null, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        console.log("첼린지 시작");
        showAlert("첼린지 시작");
        setChallengeInfo(response.data);
        handleWave(100, 100);
        setChallenge(!challenge);
      })
      .catch((error) => {
        console.error("챌린지 시작 실패", error);
      });
  };

  /**
    selectDailyChallenge
    해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트)
    /challenges?date={date}   
    GET
  */
  const selectDailyChallenge = () => {
    console.log("selectDailyChallenge");

    setIsLoading(true);

    axios
      .get(`${API_URL}${contextPath}${ChallengeAPI}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        console.log(
          "해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트)",
          response.data,
        );
        const challengeStatus = response.data.data.challengeStatus;

        console.log("challengeStatus", challengeStatus);
        if (challengeStatus === "PROGRESS") {
          setChallenge(true);
          handleWave(
            response.data.data.targetAmount,
            response.data.data.leftoverAmount,
          );
        } else {
          setChallenge(false);
        }
        setChallengeInfo(response.data.data);
        setIsLoading(false);
      })
      .catch((error) => {
        setIsLoading(false);
        console.error(
          "해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트) 조회 실패",
          error,
        );
      });
  };

  const handleWave = (targetValue: number, currentValue: number) => {
    let curAmount: number = amountPercent;
    let targetAmount: number =
      ((targetValue - currentValue) / targetValue) * 60; // min : 0(vw), max : 60(vw)

    function frame() {
      curAmount = lerp(curAmount, targetAmount, 0.02);

      setAmountPercent(curAmount);

      if (targetAmount - curAmount > 0.1 || targetAmount - curAmount < -0.1) {
        setTimeout(frame, 20);
      } else {
        setAmountPercent(targetAmount);
      }
    }

    requestAnimationFrame(frame);

    function lerp(s: number, e: number, a: number) {
      return s + (e - s) * a;
    }
  };

  //모달 컨트롤
  const handleModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  const showAlert = (text: string) => {
    Swal.fire({
      text,
    });
  };

  const navigateToSave = () => {
    navigate("/save");
  };

  const numberFormatter = (value: number) => {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };

  if (isLoading) {
    return (
      <div className="loading_text">
        <img
          src={Logo}
          alt="로딩중..."
        ></img>
      </div>
    );
  } else if (challengeInfo.challengeStatus === "SUCCESS") {
    return (
      <div className="challenge_response">
        <div className="challenge_response_container">
          <div className="challenge_response_title success">챌린지 성공</div>
          <p className="challenge_response_content">
            + {challengeInfo.leftoverAmount} 원
          </p>
          <RegisterButton
            text="추천적금 보러가기"
            onClick={navigateToSave}
            className="regular"
          />
        </div>
        <div
          className="challenge_response_wave"
          style={{ top: `${70 + waveTop}%` }}
        >
          <svg
            width="500"
            height="700"
            className="waves"
            xmlns="http://www.w3.org/2000/svg"
            xmlnsXlink="http://www.w3.org/1999/xlink"
            viewBox="0 24 150 700"
            preserveAspectRatio="none"
            shape-rendering="auto"
          >
            <defs>
              <path
                id="gentle-wave"
                d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v700h-452z"
              />
              <linearGradient
                id="wave-gradient"
                x1="0%"
                y1="0%"
                x2="0%"
                y2="100%"
              >
                <stop
                  offset="0%"
                  stop-color="var(--gradation-start)"
                />
                <stop
                  offset="100%"
                  stop-color="var(--gradation-end)"
                />
              </linearGradient>
            </defs>
            <g className="parallax">
              <use
                xlinkHref="#gentle-wave"
                x="48"
                y="0"
                fill="url(#wave-gradient)"
                opacity="20%"
              />
              <use
                xlinkHref="#gentle-wave"
                x="48"
                y="9"
                fill="url(#wave-gradient)"
                opacity="40%"
              />
              <use
                xlinkHref="#gentle-wave"
                x="48"
                y="16"
                fill="url(#wave-gradient)"
                opacity="60%"
              />
            </g>
          </svg>
        </div>
      </div>
    );
  } else if (challengeInfo.challengeStatus === "FAIL") {
    return (
      <div className="challenge_response">
        <div className="challenge_response_container fail">
          <div className="challenge_response_title">챌린지 실패</div>
          <RegisterButton
            text="추천적금 보러가기"
            onClick={navigateToSave}
            className="point_60"
          />
        </div>
      </div>
    );
  } else {
    return (
      <MainStyle>
        <div className="homepage">
          <div className="homepage_header">
            {!challenge ? (
              <>
                <p className="homepage_text">
                  {currentDate.getMonth() + 1}월 {currentDate.getDate()}일
                </p>
                <div className="homepage_header_title">
                  <h2 className="homepage_header_title_text">오늘의 챌린지</h2>
                </div>
                <p className="homepage_text">
                  {challengeInfo && challengeInfo.participantCount}명의 유저가
                  먼저 진행하고 있어요!
                </p>
              </>
            ) : (
              <>
                <p className="homepage_text">남은 시간</p>
                <div className="homepage_header_title">
                  <h2 className="homepage_header_title_text">
                    {currentDate.getHours() >= 14 && "0"}
                    {23 - currentDate.getHours()} :{" "}
                    {currentDate.getMinutes() >= 50 && "0"}
                    {59 - currentDate.getMinutes()} :{" "}
                    {currentDate.getSeconds() >= 50 && "0"}
                    {59 - currentDate.getSeconds()}
                  </h2>
                </div>
                <p className="homepage_text">
                  {challengeInfo && challengeInfo.participantCount}명의 유저와
                  함께하고 있어요!
                </p>
              </>
            )}
          </div>
          <div>
            <div className="main_circle">
              <div className="start_challenge">
                {!challenge ? (
                  <div
                    className="start_challenge_button"
                    onClick={handleStartChallenge}
                  >
                    <h2 className="start_challenge_button_title">챌린지</h2>
                    <h2 className="start_challenge_button_title">시작</h2>
                  </div>
                ) : (
                  <Link
                    to="/today"
                    className="linkto_today"
                  >
                    <div className="progress_challenge">
                      <div className="progress_challenge_title">
                        <h2>
                          {challengeInfo.leftoverAmount &&
                            numberFormatter(challengeInfo.leftoverAmount)}
                        </h2>
                        <h3>원</h3>
                      </div>
                    </div>
                    <p className="challenge_link_text">지출내역으로 이동</p>
                  </Link>
                )}
              </div>
              {
                <div
                  id="wave_height"
                  style={{ height: `${amountPercent}vw` }}
                />
              }
              <div className="wavess">
                <svg
                  width="500"
                  height="500"
                  className="waves"
                  xmlns="http://www.w3.org/2000/svg"
                  xmlnsXlink="http://www.w3.org/1999/xlink"
                  viewBox="0 24 150 500"
                  preserveAspectRatio="none"
                  shape-rendering="auto"
                >
                  <defs>
                    <path
                      id="gentle-wave"
                      d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v500h-352z"
                    />
                    <linearGradient
                      id="wave-gradient"
                      x1="0%"
                      y1="0%"
                      x2="0%"
                      y2="100%"
                    >
                      <stop
                        offset="0%"
                        stop-color="var(--gradation-start)"
                      />
                      <stop
                        offset="100%"
                        stop-color="var(--gradation-end)"
                      />
                    </linearGradient>
                  </defs>
                  <g className="parallax">
                    <use
                      xlinkHref="#gentle-wave"
                      x="48"
                      y="0"
                      fill="url(#wave-gradient)"
                      opacity="20%"
                    />
                    <use
                      xlinkHref="#gentle-wave"
                      x="48"
                      y="9"
                      fill="url(#wave-gradient)"
                      opacity="40%"
                    />
                    <use
                      xlinkHref="#gentle-wave"
                      x="48"
                      y="16"
                      fill="url(#wave-gradient)"
                      opacity="60%"
                    />
                  </g>
                </svg>
              </div>
              <div className="bottom_gradation" />
            </div>
          </div>
          <div>
            {/* <div className="getspace"/> */}
            {
              <div
                id="wave_height"
                style={{ height: `${amountPercent}vw` }}
              />
            }
            <svg
              width="500"
              height="500"
              className="waves"
              xmlns="http://www.w3.org/2000/svg"
              xmlnsXlink="http://www.w3.org/1999/xlink"
              viewBox="0 24 150 500"
              preserveAspectRatio="none"
              shape-rendering="auto"
            >
              <defs>
                <path
                  id="gentle-wave"
                  d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v500h-352z"
                />
                <linearGradient
                  id="wave-gradient"
                  x1="0%"
                  y1="0%"
                  x2="0%"
                  y2="100%"
                >
                  <stop
                    offset="0%"
                    stop-color="var(--gradation-start)"
                  />
                  <stop
                    offset="100%"
                    stop-color="var(--gradation-end)"
                  />
                </linearGradient>
              </defs>
              <g className="parallax">
                <use
                  xlinkHref="#gentle-wave"
                  x="48"
                  y="0"
                  fill="url(#wave-gradient)"
                  opacity="20%"
                />
                <use
                  xlinkHref="#gentle-wave"
                  x="48"
                  y="9"
                  fill="url(#wave-gradient)"
                  opacity="40%"
                />
                <use
                  xlinkHref="#gentle-wave"
                  x="48"
                  y="16"
                  fill="url(#wave-gradient)"
                  opacity="60%"
                />
              </g>
            </svg>
            <div className="bottom_gradation" />
          </div>
          <div className="input_purchase">
            {!challenge ? (
              <div className="information_block">
                <div className="information_block_button">
                  <p className="information_block_title">
                    챌린지 시작 가능 시간
                  </p>
                  <p className="information_block_content">05:00 ~ 12:00</p>
                </div>
              </div>
            ) : (
              <RegisterButton
                text="지출 입력"
                className="white"
                onClick={handleModal}
              />
            )}
          </div>
          {challenge && (
            <RegistModal
              isModalOpen={isModalOpen}
              modalTop={modalTop}
              setIsModalOpen={setIsModalOpen}
              challengeInfo={challengeInfo}
              setChallengeInfo={setChallengeInfo}
              handleWave={handleWave}
            />
          )}
        </div>
      </MainStyle>
    );
  }
};

export default Homepage;
