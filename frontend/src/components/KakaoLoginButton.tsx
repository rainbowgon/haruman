// import { loginKakao } from "../apis/user";

// // 카카오 로그인 버튼 클릭 시 실행되는 함수
// export const redirectKakao = () => {
//   const CLIENT_ID = "YOUR_KAKAO_APP_CLIENT_ID";
//   const REDIRECT_URI = "https://haruman.site/oauth/kakao/redirect";
//   const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;

//   // 카카오 인증 페이지로 이동
//   window.location.href = KAKAO_AUTH_URL;
// };

// // 리다이렉트된 페이지에서 실행되는 로직
// const handleKakaoAuthRedirect = async () => {
//   // URL에서 인가 코드 추출
//   const url = new URL(window.location.href);
//   const code = url.searchParams.get("code");

//   if (code) {
//     try {
//       // 인가 코드를 백엔드로 전송
//       const data = await loginKakao(code);

//       // (토큰 저장, 로그인 성공 처리 등)
//     } catch (error) {
//       console.error("카카오 로그인 실패:", error);
//     }
//   }
// };

// export default handleKakaoAuthRedirect;

import React, { useState } from "react";
import { redirectKakao, loginKakao } from "../apis/user"; // 앞서 제공된 함수들

function KakaoLoginButton() {
  const [isLoading, setIsLoading] = useState(false);

  const handleKakaoLogin = async () => {
    setIsLoading(true);

    // 카카오 로그인 페이지로 이동
    redirectKakao();
  };

  // 리다이렉트 페이지 (예: /oauth/kakao/redirect)에서 실행될 함수
  const handleKakaoAuthRedirect = async () => {
    const url = new URL(window.location.href);
    const code = url.searchParams.get("code");

    if (code) {
      try {
        const data = await loginKakao(code);

        // 여기서 필요한 후속 작업 수행 (예: 토큰 저장, 사용자 정보 표시 등)
      } catch (error) {
        console.error("카카오 로그인 실패:", error);
        setIsLoading(false);
      }
    }
  };

  return (
    <button
      onClick={handleKakaoLogin}
      disabled={isLoading}
    >
      {isLoading ? "로딩..." : "카카오로 로그인"}
    </button>
  );
}

export default KakaoLoginButton;
