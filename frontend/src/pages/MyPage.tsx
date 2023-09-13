import React from "react";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";
import ProfileImageUploader from "../components/ProfileImage";

const MyPage = () => {
  return (
    <CenterContainer>
      <MainStyle>
        <ProfileImageUploader />
      </MainStyle>
    </CenterContainer>
  );
};

export default MyPage;
