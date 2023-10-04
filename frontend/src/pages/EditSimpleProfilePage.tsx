import React, { useEffect, useState } from "react";

import "../styles/mypage/EditSimpleProfilePageStyle.scss";
import axios from "axios";
import { API_URL } from "../constants/urls";
import RegisterButton from "../components/RegistButton";

// apis
const contextPath = `/api`;
const ProfileAPI = "/profiles";

const EditSimpleProfilePage = () => {
  // 테스트용
  const accessToken = process.env.REACT_APP_accessToken;
  // 배포용
  // const accessToken = sessionStorage.getItem("accessToken");

  const [user, setSUser] = useState({
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
      .get(`${API_URL}${contextPath}${ProfileAPI}/1`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        console.log("회원 프로필 조회 성공", response.data.data);
        setSUser(response.data.data);
      })
      .catch((error) => {
        console.error("회원 프로필 조회 실패", error);
      });
  };

  // 챌린지 관련
  /**
      updateProfile   
      회원 프로필 수정   
      /profiles/{profile-id}?nickname={nickname}&profileImage={multipartImage} 
      PATCH
  */

  const updateProfile = () => {
    //   axios.patch(`${API_URL}${contextPath}${ProfileAPI}?nickname=${nickname}&profileImage=${multipartImage}`, null,
    axios
      .patch(
        `${API_URL}${contextPath}${ProfileAPI}?nickname=nickname수정&profileImage=null`,
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

  const onClickCancel = () => {};
  const onClickSuccess = () => {
    updateProfile();
  };

  return (
    <div className="edit_simple_profile_container">
      <div className="edit_simple_profile_img">
        {user && user.profileImage && (
          <img
            className="profile_img"
            src={`${user && user.profileImage}`}
            alt="프로필 이미지"
          />
        )}
      </div>
      <p className="simple_profile_nickname_text">{user && user.nickname}</p>
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
          className="regular brand"
          onClick={onClickCancel}
        />
        <RegisterButton
          text="완료"
          className="regular white"
          onClick={onClickSuccess}
        />
      </div>
    </div>
  );
};

export default EditSimpleProfilePage;
