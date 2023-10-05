import React, { useEffect, useState } from "react";

import "../styles/mypage/EditSimpleProfilePageStyle.scss";
import axios from "axios";
import { API_URL } from "../constants/urls";
import RegisterButton from "../components/RegistButton";
import { useNavigate } from "react-router-dom";

// apis
const contextPath = `/api`;
const ProfileAPI = "/profiles";

const EditSimpleProfilePage = () => {
  const accessToken = process.env.REACT_APP_accessToken;
  // 배포용
  // const accessToken = localStorage.getItem("accessToken");

  const navigate = useNavigate();

  const [user, setUser] = useState({
    profileId: 30,
    nickname: "테스트",
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

  // 챌린지 관련
  /**
      updateProfile   
      회원 프로필 수정   
      /profiles?nickname={nickname}&profileImage={multipartImage}
      PATCH
  */

  const updateProfile = () => {
    axios
      .patch(
        `${API_URL}${contextPath}${ProfileAPI}?nickname=${user.nickname}&profileImage=null`,
        null,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        },
      )
      .then((response) => {
        console.log("프로필이 수정되었어요", response.data);
      })
      .catch((error) => {
        console.error("프로필 수정 실패", error);
      });
  };

  const onClickCancel = () => {
    navigate(-1);
  };
  const onClickSuccess = () => {
    updateProfile();
  };

  return (
    <div>
      <div className="edit_simple_profile_container">
        <div className="edit_simple_profile_img_div">
          {user && user.profileImage && (
            <img
              className="edit_simple_profile_img"
              src={`${user && user.profileImage}`}
              alt="프로필 이미지"
            />
          )}
        </div>
        <input
          className="simple_profile_nickname_text"
          value={user && user.nickname}
          onChange={(e) => setUser({ ...user, nickname: e.target.value })}
        />
        <div className="simple_profile_rule_box">
          <h3 className="simple_profile_rule_header_text">닉네임 생성 규칙</h3>
          <p className="simple_profile_rule_text">최소 2자, 최대 12자</p>
          <p className="simple_profile_rule_text">
            ';.`*"등의 특수문자 사용 불가
          </p>
        </div>
        <div className="simple_profile_buttons">
          <RegisterButton
            text="취소"
            className="regular white"
            onClick={onClickCancel}
          />
          <RegisterButton
            text="완료"
            className="regular brand"
            onClick={onClickSuccess}
          />
        </div>
      </div>
    </div>
  );
};

export default EditSimpleProfilePage;