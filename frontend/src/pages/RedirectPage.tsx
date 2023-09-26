import React, { useEffect } from "react";
import { loginKakao } from "../apis/user";

function KakaoCallback() {
  useEffect(() => {
    const processLogin = async () => {
      const url = new URL(window.location.href);
      const code = url.searchParams.get("code");
      if (code) {
        try {
          const data = await loginKakao(code);
          localStorage.setItem("token", data.token);
          window.location.href = "/home";
        } catch (error) {
          console.error("카카오 로그인 실패:", error);
        }
      }
    };

    processLogin();
  }, []);

  return <div>카카오 로그인 처리중...</div>;
}

export default KakaoCallback;
