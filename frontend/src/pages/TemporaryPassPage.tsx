import React, { useState } from "react";
import { styled } from "styled-components";
import { useNavigate } from "react-router";
import Input from "../components/InputText";
import RegistButton from "../components/RegistButton";
import { sendTemporaryPassword } from "../apis/email";
import { Link } from "react-router-dom";
import useErrorHandlers from "../hooks/useError";

const StyledLink = styled(Link)`
  text-decoration: none;
  color: inherit;
`;

const ButtonContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  text-align: center;
`;

const ResetPasswordPage = () => {
  const [email, setEmail] = useState("");
  const errorHandlers = useErrorHandlers();

  const handleEmailSender = () => {
    sendTemporaryPassword(email)
      .then((res) => {
        alert("이메일로 임시 비밀번호가 발송되었습니다.");
        console.log(res);
        // 로그인 페이지로 리다이렉트
        RedirectLogin();
      })
      .catch((err) => {
        errorHandlers(err.response, handleEmailSender);
      });
  };

  const naviagte = useNavigate();

  const RedirectLogin = () => {
    naviagte("/");
  };

  return (
    <>
      <Input
        type="email"
        placeholder="이메일을 입력하세요"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <ButtonContainer>
        <RegistButton
          text="이메일 발송"
          onClick={handleEmailSender}
        />
      </ButtonContainer>
    </>
  );
};

export default ResetPasswordPage;
