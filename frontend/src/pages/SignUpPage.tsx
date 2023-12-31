import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

// import { registServer } from "../apis/user";

import Input from "../components/InputText";
import RegisterButton from "../components/RegistButton";
import CheckBox from "../components/CheckBox";
import Info from "../components/InfoText";
// import { sendAuthenticationCode } from "../apis/email";
// import useErrorHandlers from "../hooks/useError";
import BirthDatePick from "../components/DatePicker/index";

import "../styles/theme.css";
import LogoImage from "../assets/logo-mainlogo.svg";

import AgreementContent1 from "../components/AgreementContent1";
import AgreementContent2 from "../components/AgreementContent2";
import styled from "styled-components";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";

//icon
import ArrowLeft from "../assets/icons/icon-arrowleft.svg";
import { Link } from "react-router-dom";

//scss
import "../styles/user/SignUpPageStyle.scss";

const LogoDiv = styled.div`
  margin: 5vh 0vh 5vh;
`;

const SignupPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [nickname, setNickName] = useState("");
  const [passwordCheck, setPasswordCheck] = useState("");
  const [birthDate, setBirthDate] = useState<Date | null>(null);
  const [pwFlag, setPwFlag] = useState(false);
  const [emailSend, setEmailSend] = useState(false);
  const [emailAuthentication, setEmailAuthentication] = useState(false);
  const [authenticationCode, setAuthenticationCode] = useState("");
  const [userCode, setUserCode] = useState("");
  const [agreeCheck1, setAgreeCheck1] = useState(false);
  const [agreeCheck2, setAgreeCheck2] = useState(false);

  const userData = {
    userEmail: email,
    userName: name,
    userPass: password,
    userNickName: nickname,
    userBirth: birthDate,
  };

  const handleDateChange = (date: Date | null) => {
    setBirthDate(date);
  };
  // const errorHandlers = useErrorHandlers();

  const handleSignup = async () => {
    let err = false;
    let msg = "";
    // 이메일 정규표현식
    let emailReg =
      /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    // 비밀번호 정규표현식 - 8~15자 영문 숫자 특수문자
    let pwReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;

    if (!email) {
      msg = "이메일을 입력해주세요";
      err = true;
    } else if (!err && !email.match(emailReg)) {
      msg = "올바른 이메일을 입력해주세요";
      err = true;
    } else if (!err && password.length === 0) {
      msg = "비밀번호를 입력해주세요";
      err = true;
    } else if (!err && !password.match(pwReg)) {
      msg =
        "비밀번호는 8자 이상 15자 이하 영문/특수문자/숫자 조합이어야 합니다";
      err = true;
    } else if (!err && passwordCheck.length === 0) {
      msg = "비밀번호 확인을 입력해주세요";
      err = true;
    } else if (!err && !name) {
      msg = "이름을 입력해주세요";
      err = true;
    } else if (!err && !pwFlag) {
      msg = "비밀번호가 일치하지 않습니다";
      err = true;
    } else if (!err && !emailAuthentication) {
      msg = "이메일 인증이 진행되지 않았습니다";
      err = true;
    }

    if (err) {
      alert(msg);
    } else {
      handleRegist();
    }
  };

  const handleRegist = () => {};

  const handleEmailSender = () => {};

  const handleEmailAuthentication = () => {
    if (authenticationCode === userCode) {
      alert("인증 성공");
      setEmailAuthentication(true);
    } else {
      alert("인증 코드가 일치하지 않습니다");
    }
  };

  const handlePasswordCheckChange = (e: any) => {
    setPasswordCheck(e.target.value);
    setPwFlag(e.target.value === password);
  };

  const naviagte = useNavigate();

  const RedirectLogin = () => {
    naviagte("/");
  };
  // const [isDisabled, setIsDisabled] = useState(false); // 장애 여부 체크 상태를 state로 관리

  return (
    <>
      <div className="signup_back_div">
        <Link
          className="signup_back"
          to="/login"
        >
          <img
            className="signup_back_img"
            src={ArrowLeft}
            alt="뒤로가기"
          />
        </Link>
      </div>
      {agreeCheck1 && agreeCheck2 ? (
        <CenterContainer>
          <MainStyle>
            <div>
              <LogoDiv>
                <img
                  src={LogoImage}
                  alt="로고 이미지"
                />
              </LogoDiv>
              <Input
                type="email"
                placeholder="이메일"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              {!emailSend && !emailAuthentication && (
                <RegisterButton
                  text="이메일 인증 코드 발송"
                  onClick={handleEmailSender}
                />
              )}
              {emailSend && !emailAuthentication && (
                <>
                  <Input
                    type="text"
                    placeholder="인증코드 입력"
                    value={userCode}
                    onChange={(e) => setUserCode(e.target.value)}
                  />
                  <RegisterButton
                    text="이메일 인증"
                    onClick={handleEmailAuthentication}
                  />
                </>
              )}
              <Input
                type="text"
                placeholder="이름"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
              <Input
                type="text"
                placeholder="닉네임"
                value={nickname}
                onChange={(e) => setNickName(e.target.value)}
              />
              <Input
                type="password"
                placeholder="비밀번호"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <Input
                type="password"
                placeholder="비밀번호 확인"
                value={passwordCheck}
                onChange={handlePasswordCheckChange}
              />
              {pwFlag === false && passwordCheck.length !== 0 && (
                <Info text="비밀번호가 일치하지 않습니다." />
              )}
              <BirthDatePick
                birthDate={birthDate}
                setBirthDate={setBirthDate}
              />

              <RegisterButton
                text="Join Us!"
                onClick={handleSignup}
              />
            </div>
          </MainStyle>
        </CenterContainer>
      ) : (
        <>
          <div
            className="agreement_container"
            id="AgreementContainer"
          >
            <div className="agreement_logo_img">
              <img
                src={LogoImage}
                alt="로고 이미지"
              />
            </div>
            <h1 className="service_guide_title">서비스 이용약관</h1>
            <AgreementContent1 />
            <br />
            <CheckBox
              label="동의함"
              checked={agreeCheck1}
              onChange={(e) => setAgreeCheck1(e.target.checked)}
            />
            <br />
            <AgreementContent2 />
            <br />
            <CheckBox
              label="동의함"
              checked={agreeCheck2}
              onChange={(e) => setAgreeCheck2(e.target.checked)}
            />
          </div>
        </>
      )}
    </>
  );
};

export default SignupPage;
