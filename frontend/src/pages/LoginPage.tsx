import React, { useEffect } from "react";
import MainStyle from "../components/MainStyle";
import InputText from "../components/InputText";
import CenterContainer from "../components/CenterContainer";
import RegisterButton from "../components/RegistButton";
import { Link } from "react-router-dom";
import LogoImage from "../assets/logo-mainlogo.svg";

//로그인 관련

import { redirectGoogle } from "../apis/user";
import { userSliceLogin } from "../stores/userSlice";
import { HttpStatusCode } from "axios";
import { useState } from "react";
import { useAppDispatch } from "../hooks/reduxHook";
import { useNavigate } from "react-router-dom";
import { redirectKakao } from "../apis/user";

// style
import "../styles/theme.css";
import Checkbox from "../components/CheckBox";
import styled from "styled-components";

//scss
import "../styles/user/LoginPageStyle.scss";
import LoginButton from "../components/LoginButton";

const StyledDiv = styled.div`
  margin-left: 10vw;
  text-align: left;
  color: var(--brand1_main);
`;

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [autoLogin, setAutoLogin] = useState(false);
  const [waveTop, setWaveTop] = useState(100);

  const userData = {
    userEmail: email,
    userPass: password,
  };
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

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

  const handleLogin = () => {
    if (email === "") {
      alert("이메일을 입력해주세요");
    } else if (password === "") {
      alert("비밀번호를 입력해주세요");
    } else {
      // loginServer(userData)
      //   .then((res) => {
      //     alert("로그인 성공");
      //     // userSlice에 저장
      //     dispatch(userSliceLogin(res));
      //     // 메인페이지로 리다이렉트
      //     if (autoLogin) {
      //       // 자동 로그인이 활성화된 경우
      //       // 로그인 후 자동으로 메인페이지로 리다이렉트
      //       RedirectHomePage();
      //     } else {
      //       // 자동 로그인이 비활성화된 경우
      //       // 일반 로그인 후 리다이렉트
      //       navigate("/home");
      //     }
      //   })
      //   .catch((err) => {
      //     if (err.response === undefined) {
      //       navigate("/error");
      //       return;
      //     }
      //     handleError(err.response.status);
      //   });
    }
  };
  const RedirectTemporaryPass = () => {
    navigate("/pass");
  };

  const handleRegist = () => {
    navigate("/signup");
  };

  const RedirectHomePage = () => {
    navigate("/home");
  };

  // const handleKeyPress = (e: KeyboardEvent<HTMLInputElement>) => {
  //   if (e.key === 'Enter') {
  //     handleLogin();
  //   }
  // };

  const handleError = (statusCode: HttpStatusCode | undefined) => {
    switch (statusCode) {
      case HttpStatusCode.Unauthorized:
        alert("이메일과 비밀번호를 확인해주세요");
        break;
      default:
        navigate("/error");
    }
  };
  return (
    <CenterContainer>
      <MainStyle>
        <div className="login_page">
          <div className="login_header">
            <div
              className="login_wave"
              style={{ top: `${40 + waveTop}%` }}
            >
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
            <div className="introduce_div">
              <h1 className="introduce_title">하루만</h1>
              <p className="introduce_text">
                하루 만 원으로 시작해보는
                <br />
                당신의 절약습관
              </p>
            </div>
          </div>
          <div className="max_div">
            <div className="logo_div">
              <img
                src={LogoImage}
                alt="로고 이미지"
              />
            </div>
            <div className="oauth_links">
              <div className="oauth_line">
                <div className="oauth_text">easy to start</div>
                <LoginButton
                  type="kakao"
                  value="카카오"
                />
                <LoginButton
                  type="google"
                  value="구글"
                />
                <LoginButton
                  type="email"
                  value="E-mail"
                />
              </div>
            </div>
          </div>
        </div>
      </MainStyle>
    </CenterContainer>
  );
};

export default LoginPage;
