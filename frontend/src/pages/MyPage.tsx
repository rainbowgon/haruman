import React, {useState} from "react";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";
import ProfileImageUploader from "../components/ProfileImage";
import RegisterButton from "../components/RegistButton";

// style
import "../styles/MyPageStyle.scss"

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
        <div className="profile_container">
          <div>
            <div>
              <p>하루만과 함께한지 {}일이 지났네요</p>
            </div>
            <div>
              <div className="regular_container">
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
                  className=""  
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
