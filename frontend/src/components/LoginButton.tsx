import { useState } from "react";
import axios from "axios";
import { redirectKakao, loginKakao } from "../apis/user";


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

  return (
    <button
      className={`LoginButton ${type}`}
      onClick={handleLogin}
      disabled={isLoading}
    >
      {isLoading ? "로딩..." : `${value}로 로그인`}
    </button>
  );
}

export default LoginButton;
