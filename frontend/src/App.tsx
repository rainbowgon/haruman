import React from "react";
import styled from "styled-components";
import "./App.css";

// pages
import HomePage from "./pages/HomePage";
import LandingPage from "./pages/LandingPage";
import SignUpPage from "./pages/SignUpPage";
import MyPage from "./pages/MyPage";
import CalendarPage from "./pages/CalendarPage";
import LoginPage from "./pages/LoginPage";

// redux
import { useAppSelector } from "./hooks/reduxHook";

// react-router-dom
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

const AppContainer = styled.div`
  height: 100vh;
  width: 100vw;
  overflow-x: hidden;
  // 가로 이동 없을거니까
`;

const App: React.FC = () => {
  // const isLogin = useAppSelector((state) => state.user.isLogin);

  return (
    <>
      <AppContainer>
        <Router>
          <Routes>
            {/*   로그인 및 회원가입 기능 이후에 로그인 처리 안되면 랜딩페이지로
          <Route path="/" element={isLogin ? <LandingPage /> : <LandingPage />} />
          <Route path="/signup" element={isLogin ? <SignUpPage /> : <SignUpPage />} />
          <Route path="/home" element={isLogin ? <HomePage /> : <LandingPage />} />
          <Route path="/mypage" element={isLogin ? <MyPage /> : <LandingPage />} />
          */}

            <Route
              path="/"
              element={<LandingPage />}
            />
            <Route
              path="/home"
              element={<HomePage />}
            />
            <Route
              path="/signup"
              element={<SignUpPage />}
            />
            <Route
              path="/mypage"
              element={<MyPage />}
            />
            <Route
              path="/calendar"
              element={<CalendarPage />}
            />
            <Route
              path="/login"
              element={<LoginPage />}
            ></Route>
          </Routes>
        </Router>
      </AppContainer>
    </>
  );
};

export default App;
