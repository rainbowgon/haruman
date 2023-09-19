import React from "react";
import MainStyle from "../components/MainStyle";
import InputText from "../components/InputText";
import CenterContainer from "../components/CenterContainer";
import RegisterButton from "../components/RegistButton";
import { Link } from "react-router-dom";
import LogoImage from "../assets/logo-mainlogo.svg";

//로그인 관련

import { loginServer, redirectGoogle } from "../apis/user";
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

const StyledDiv = styled.div`
  margin-left: 10vw;
  text-align: left;
  color: var(--brand1_main);
`;

const LogoDiv = styled.div`
  margin: 10vh 0vh 10vh;
`;

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [autoLogin, setAutoLogin] = useState(false);
  const userData = {
    userEmail: email,
    userPass: password,
  };
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const handleLogin = () => {
    if (email === "") {
      alert("이메일을 입력해주세요");
    } else if (password === "") {
      alert("비밀번호를 입력해주세요");
    } else {
      loginServer(userData)
        .then((res) => {
          alert("로그인 성공");
          // userSlice에 저장
          dispatch(userSliceLogin(res));
          // 메인페이지로 리다이렉트
          if (autoLogin) {
            // 자동 로그인이 활성화된 경우
            // 로그인 후 자동으로 메인페이지로 리다이렉트
            RedirectHomePage();
          } else {
            // 자동 로그인이 비활성화된 경우
            // 일반 로그인 후 리다이렉트
            navigate("/home");
          }
        })
        .catch((err) => {
          if (err.response === undefined) {
            navigate("/error");
            return;
          }
          handleError(err.response.status);
        });
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
        <LogoDiv>
          <img
            src={LogoImage}
            alt="로고 이미지"
          />
        </LogoDiv>
        <InputText
          className="InputText"
          type="email"
          // alt="Input Email"
          placeholder="이메일"
          value={email}
          // 추후 email이 들어온다면 sending
          onChange={(e) => setEmail(e.target.value)}
          // onKeyPress={handleKeyPress}
        />
        <InputText
          className="InputText"
          // alt="password"
          type="password"
          placeholder="비밀번호"
          value={password}
          // 추후 password 설정시 주석 해제
          onChange={(e) => setPassword(e.target.value)}
          // onKeyPress={handleKeyPress}
        />
        <Checkbox
          label="자동 로그인"
          checked={autoLogin}
          onChange={() => setAutoLogin(!autoLogin)}
        />
        <RegisterButton
          text="로그인"
          onClick={handleLogin}
        />
        <div className="max_div">
          <div className="user_access_links">
            <div className="others_links">
              <div className="link_left">
                <Link
                  className="others_link"
                  to="/finduserid"
                >
                  아이디 찾기{" "}
                </Link>
                <Link
                  className="others_link"
                  to="/temp"
                >
                  {" "}
                  / 비밀번호 발급
                </Link>
              </div>
              <div className="link_right">
                <Link
                  className="others_link"
                  to="/signup"
                >
                  회원가입
                </Link>
              </div>
            </div>
          </div>
        </div>
        <div className="max_div">
          <div className="oauth_links">
            <div className="oauth_line">
              <div className="oauth_text">easy to start</div>
              <div className="oauth_login">
                <RegisterButton
                  text="Kakao"
                  className="mini_type"
                  onClick={redirectKakao}
                />
                <RegisterButton
                  text="Google"
                  className="mini_type"
                  onClick={redirectGoogle}
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
