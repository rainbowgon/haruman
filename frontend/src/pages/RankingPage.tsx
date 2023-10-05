import React, { useEffect, useState } from "react";
import BubbleChart, { DataPoint } from "../components/BubbleChart";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";
import axios from "axios";

// styles
import "../styles/RankinPageStyle.scss";
import "../styles/components/MiddleStyle.scss";
import BottomBarSpace from "../components/BottomBarSpace";
import { API_URL } from "../constants/urls";
import HeaderTitle from "../components/HeaderTitle";
import UserItem from "../components/UserItem";
import MiddleTitle from "../components/MiddleTitle";

interface User {
  nickname: string;
  profileImage: string | null;
  usedAmount: number;
  challengeStartTime: string;
  latestExpensePayTime: string | null;
}

interface Group {
  groupKey: string;
  userList: User[];
}

interface ApiResponse {
  pageInfo: {
    page: number;
    size: number;
    totalElements: number;
    totalPages: number;
  };
  message: string;
  data: Group[];
}

const RankingPage = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const [groups, setGroups] = useState<Group[]>([]);
  const [selectedUsers, setSelectedUsers] = useState<User[]>([]);
  const [selectedBubbleLabel, setSelectedBubbleLabel] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      // 배포용
      const accessToken = localStorage.getItem("accessToken");
      console.log("토큰", accessToken);
      try {
        const response = await axios.get<ApiResponse>(
          `${API_URL}/api/challenges/people`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          },
        );
        setGroups(response.data.data);
        console.log("API 응답:", response.data.data);
      } catch (error) {
        console.error("에러 메시지 : ", error);
      }
    };

    fetchData();
  }, []);

  const handleBubbleClick = (data: DataPoint) => {
    console.log("버블 클릭 데이터:", data);
    setSelectedBubbleLabel(data.label);
    const matchingGroup = groups.find((group) => group.groupKey === data.label);

    if (matchingGroup) {
      setSelectedUsers(matchingGroup.userList);
    }
  };

  return (
    <CenterContainer>
      <MainStyle>
        <div className="rankingpage">
          <HeaderTitle
            SubTitle={`오늘의 챌린지`}
            MainTitle={`${
              currentDate.getMonth() + 1
            }월 ${currentDate.getDate()}일`}
          />
          <BubbleChart onBubbleClick={handleBubbleClick} />
          <MiddleTitle
            SubTitle={` ${selectedBubbleLabel} 버블에 있는 유저예요! `}
            MainTitle={``}
          />
          <div className="challengeitems_list">
            {selectedUsers.map((user, index) => (
              <UserItem
                key={index}
                image={user.profileImage}
                mainValue={user.nickname}
                moneyValue={user.usedAmount}
              />
            ))}
          </div>
        </div>
        <BottomBarSpace />
      </MainStyle>
    </CenterContainer>
  );
};

export default RankingPage;
