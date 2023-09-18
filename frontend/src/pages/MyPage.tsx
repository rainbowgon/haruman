import React, {useState} from "react";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";
import ProfileImageUploader from "../components/ProfileImage";
import RegisterButton from "../components/RegistButton";

const MyPage = () => {

  const [features, setFeatures] = useState(["HomePage", "TodayExpensePage", "CalendarPage", "RankingPage", "plus"]);
  
  const handleEditProfile = () => {

  }

  const handleEditCategory = () => {

  }

  return (
    <CenterContainer>
      <MainStyle>
        <ProfileImageUploader />
        <div>
          <p>하루만과 함께한지 {}일이 지났네요</p>
        </div>
        <div>
          <div>
            <RegisterButton
              text="프로필 수정"
              className="regular_type brand"
              onClick={handleEditProfile}
            />
            <RegisterButton
              text="카테고리 수정"
              className="regular_type"
              onClick={handleEditCategory}
            />
          </div>
        </div>
        <div>
          { features.map((feature) => (
            <RegisterButton
              text={feature}
              className="regular_type"  
              onClick={handleEditCategory}
            />
          ))}
        </div>
        <div>
          <RegisterButton
            text="로그아웃"
            className="regular_type highlight"
            onClick={handleEditCategory}
          />
        </div>
      </MainStyle>
    </CenterContainer>
  );
};

export default MyPage;
