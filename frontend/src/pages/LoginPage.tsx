import React from "react";
import MainStyle from "../components/MainStyle";
import InputText from "../components/InputText";
import CenterContainer from "../components/CenterContainer";
import RegisterButton from "../components/RegistButton";
import { Link } from "react-router-dom";
import "../styles/theme.css";

//로그인 관련

import { loginServer } from "../apis/user";
import { userSliceLogin } from "../stores/userSlice";
import { HttpStatusCode } from "axios";
import { useState } from "react";
import { useAppDispatch } from "../hooks/reduxHook";
import { useNavigate } from "react-router-dom";
import Checkbox from "../components/CheckBox";
import styled from "styled-components";

const StyledDiv = styled.div`
  margin-left: 10vw;
  text-align: left;
  color: var(--brand1_main);
`;

const StyledLink = styled(Link)`
  text-decoration: none;
  color: var(--brand1_main);
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
        <div>
          <h1>로고 영역</h1>
        </div>
        <StyledDiv>email</StyledDiv>
        <InputText
          className="InputText"
          type="email"
          // alt="Input Email"
          placeholder="이메일을 입력하세요"
          value={email}
          // 추후 email이 들어온다면 sending
          onChange={(e) => setEmail(e.target.value)}
          // onKeyPress={handleKeyPress}
        />
        <StyledDiv>password</StyledDiv>
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
        <StyledDiv>
          <Checkbox
            label="자동 로그인"
            checked={autoLogin}
            onChange={() => setAutoLogin(!autoLogin)}
          />
        </StyledDiv>
        <RegisterButton
          text="로그인"
          onClick={handleLogin}
        />
        <StyledLink to="/finduserid">아이디 찾기 / </StyledLink>
        <StyledLink to="/temp">임시 비밀번호 발급</StyledLink>
        <StyledLink to="/signup">회원가입</StyledLink>
        <hr />
        <StyledDiv>easy to start</StyledDiv>
        <RegisterButton
          text="Kakao"
          onClick={handleLogin}
        />
        <RegisterButton
          text="Google"
          onClick={handleLogin}
        />
      </MainStyle>
    </CenterContainer>
  );
};

export default LoginPage;
