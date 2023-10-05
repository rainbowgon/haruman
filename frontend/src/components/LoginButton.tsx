import { useState } from "react";
import axios from "axios";
import { redirectKakao, loginKakao, redirectGoogle } from "../apis/user";

// icons
import Kakao from "../assets/icons/oauth-kakao.svg";
import Google from "../assets/icons/oauth-google.svg";
import Email from "../assets/icons/icon-email.svg";

// scss
import "../styles/user/LoginButtonStyle.scss";
import Swal from "sweetalert2";

export interface LoginProps {
  type: string;
  value: string;
}

function LoginButton({ type, value }: LoginProps) {
  const [isLoading, setIsLoading] = useState(false);

  const handleKakaoLogin = async () => {
    setIsLoading(true);
    redirectKakao();
  };

  const handleGoogleLogin = async () => {
    setIsLoading(true);
    redirectGoogle();
  };

  const handleLogin = () => {
    if (type === "kakao") {
      handleKakaoLogin();
    } else if (type === "google") {
      // showAlert(`google 로그인은 준비중이에요!`);
      handleGoogleLogin();
    } else {
      showAlert(`Email 로그인은 준비중이에요!`);
      // handleEmail();
    }
  };

  function handleSelectIcon() {
    if (type === "kakao") {
      return Kakao;
    } else if (type === "google") {
      return Google;
    } else {
      return Email;
    }
  }

  const showAlert = (text: string) => {
    Swal.fire({
      text,
    });
  };

  return (
    <button
      className={`LoginButton ${type}`}
      onClick={handleLogin}
      disabled={isLoading}
    >
      <img
        className="login_icon"
        src={`${handleSelectIcon()}`}
        alt={`${type} icon`}
      ></img>
      {isLoading ? "로딩중" : `${value} 로그인`}
    </button>
  );
}

export default LoginButton;
