import { useState } from "react";
import axios from "axios";
import { redirectKakao, loginKakao } from "../apis/user";

import Kakao from "../assets/icons/oauth-kakao.svg"
import Google from "../assets/icons/oauth-google.svg"
import Email from "../assets/icons/icon-email.svg"

// scss
import "../styles/user/LoginButtonStyle.scss"

export interface LoginProps {
  type: string;
  value : string;
}

function LoginButton({
    type,
    value
  } : LoginProps) {
  const [isLoading, setIsLoading] = useState(false);

  const handleKakaoLogin = async () => {
    setIsLoading(true);
    redirectKakao();
  };

  const handleLogin = () => {
    console.log("handleLogin");
    if (type === "kakao"){
      console.log("handleKakaoLogin");
      handleKakaoLogin();
    } else if (type === "google") {
      //handleGoogleLogin();
    } else {
      // handleEmail();
    }
  }

  function handleSelectIcon() {
    
    if (type === "kakao"){
      return Kakao;
    } else if (type === "google"){
      return Google;
    } else {
      return Email;
    }
  }

  return (
    <button
      className={`LoginButton ${type}`}
      onClick={handleLogin}
      disabled={isLoading}
    >
      <img className="login_icon" src={`${handleSelectIcon()}`} alt={`${type} icon`}></img>
      {isLoading ? "로딩..." : `${value} 로그인`}
    </button>
  );
}

export default LoginButton;
