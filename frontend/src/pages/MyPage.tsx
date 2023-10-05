import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router";

// style
import "../styles/MyPageStyle.scss";

// icons
import Edit from "../assets/icons/icon-edit.svg";

// components
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";
import ProfileImageUploader from "../components/ProfileImage";
import RegisterButton from "../components/RegistButton";
import ShortcutButton from "../components/ShortcutButton";
import axios from "axios";
import { API_URL } from "../constants/urls";

// apis
const contextPath = `/api`;
const ProfileAPI = "/profiles";

const MyPage = () => {
  // 테스트용
  // const accessToken = process.env.REACT_APP_accessToken;
  // 배포용
  const accessToken = sessionStorage.getItem("accessToken");

  const navigate = useNavigate();

  const [features, setFeatures] = useState([
    "HomePage",
    "TodayExpensePage",
    "CalendarPage",
    "RankingPage",
    "plus",
  ]);
  const [user, setUser] = useState({
    profileId: 30,
    nickname: "명정루",
    profileImage: null,
  });

  useEffect(() => {
    selectOneProfile();
  }, []);

  /**
   * selectOneProfile
   * 회원 프로필 조회
   * /profiles/{profile-id}
   * GET
   */
  const selectOneProfile = () => {
    console.log("selectOneProfile");

    axios
      .get(`${API_URL}${contextPath}${ProfileAPI}/mine`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        console.log("회원 프로필 조회 성공", response.data.data);
        setUser(response.data.data);
      })
      .catch((error) => {
        console.error("회원 프로필 조회 실패", error);
      });
  };

  const handleEditSimpleProfile = () => {
    console.log("handEditSimpleProfile");
    navigate("/mypage/editsimpleprofile");
  };

  const handleEditProfile = () => {
    console.log("handEditProfile");
  };

  const handleEditCategory = () => {
    navigate("/error");
  };

  return (
    <CenterContainer>
      <MainStyle>
        <ProfileImageUploader />
        <div className="mypage_container">
          <div>
            <div>
              <div className="profile_container">
                <div className="profile_img_container">
                  <button
                    className="profile_edit_button"
                    onClick={handleEditSimpleProfile}
                  >
                    <img
                      className="profile_edit_button_img"
                      src={Edit}
                      alt="수정 아이콘"
                    />
                  </button>
                  {user && user.profileImage && (
                    <img
                      className="profile_img"
                      src={`${user && user.profileImage}`}
                      alt="프로필 이미지"
                    />
                  )}
                </div>
                <div className="profile_text">
                  <p className="profile_nickname_text">
                    {user && user.nickname}
                  </p>
                  <p className="profile_content_text">
                    하루만과 함께한지 {}일이 지났네요
                  </p>
                </div>
              </div>
            </div>
            <div>
              <div className="regular_container">
                <RegisterButton
                  text="프로필 수정"
                  className="regular brand"
                  onClick={handleEditProfile}
                />
                <RegisterButton
                  text="카테고리 수정"
                  className="regular white"
                  onClick={handleEditCategory}
                />
              </div>
            </div>
            <div className="shortcuts_container">
              {features.map((feature) => (
                <ShortcutButton
                  text={feature}
                  className="square"
                  onClick={handleEditCategory}
                />
              ))}
            </div>
          </div>

          <div>
            <RegisterButton
              text="로그아웃"
              className="highlight"
              onClick={handleEditCategory}
            />
          </div>
        </div>
      </MainStyle>
    </CenterContainer>
  );
};

export default MyPage;
