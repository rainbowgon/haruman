import { useState } from "react";
import axios from "axios";
import { redirectKakao, loginKakao } from "../apis/user";

function KakaoLoginButton() {
  const [isLoading, setIsLoading] = useState(false);

  const handleKakaoLogin = async () => {
    setIsLoading(true);

    // 1. 카카오 로그인 페이지로 이동
    redirectKakao();

    // URL에 인증 코드가 있는지 체크 (실제로는 리다이렉트 후 새로운 페이지/컴포넌트에서 수행됩니다)
    // const url = new URL(window.location.href);
    // const code = url.searchParams.get("code");

    // if (code) {
    //   try {
    // 2. 백엔드 서버에 로그인 요청
    // const data = await loginKakao(code);

    // data 처리 (예: 로컬 스토리지에 토큰 저장)
    // localStorage.setItem("token", data.token);

    // 3. 사용자를 대시보드 페이지 등으로 리다이렉트
    // window.location.href = "/home";
    // } catch (error) {
    // console.error("카카오 로그인 실패:", error);
    // setIsLoading(false);
    // }
    // }
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
