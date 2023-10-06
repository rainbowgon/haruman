import { API_URL, SERVER_URL } from "../constants/urls";
import { axiosInstance } from "./instance";

/* -----------------------------------------------------------------------------------*/

// 카카오 로그인 리다이렉트
export const redirectKakao = () => {
  const REDIRECT_URI = `${API_URL}/api/oauth/kakao`;
  window.location.href = REDIRECT_URI;
};

// 카카오 로그인
export const loginKakao = async (code: string) => {
  const res = await axiosInstance.get<any>(
    `/api/oauth/kakao/login?code=${code}`,
  );
  return res.data;
};

// 구글 로그인 리다이렉트
export const redirectGoogle = () => {
  const REDIRECT_URI = `${API_URL}/api/oauth/google`;
  window.location.href = REDIRECT_URI;
};

// 구글 로그인
export const loginGoogle = async (code: string) => {
  const res = await axiosInstance.get<any>(
    `/api/oauth/google/login?code=${code}`,
  );
  return res.data;
};

// // 탈퇴하기
// export const withdraw = async () => {
//   await axiosInstance.delete<any>(`/user`);
// };

// // accessToken 토큰 갱신
// export const reissueToken = async () => {
//   const res = await axiosInstance.post<any>(`/user/login/token`);
//   return res.data;
// };

// // 회원 정보 받기
// export const getUserDetail = async (userId?: number) => {
//   const queryParam = userId !== undefined ? `?userId=${userId}` : "";
//   const res = await axiosInstance.get(`/user/${queryParam}`);
//   return res.data;
// };

// // 닉네임 중복 검사
// export const checkNickname = async (nickname: string) => {
//   const res = await axiosInstance.get(`/user/nickname/${nickname}`);
//   return res.data;
// };

// // 유튜브 로그인 리다이렉트
// export const redirectYoutube = () => {
//   const CLIENT_ID = "";
//   const REDIRECT_URI = `${SERVER_URL}/upload`;
//   const GOOGLE_Upload_URL = `https://accounts.google.com/o/oauth2/auth/oauthchooseaccount?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code&scope=https://www.googleapis.com/auth/youtube.upload`;
//   window.location.href = GOOGLE_Upload_URL;
// };
