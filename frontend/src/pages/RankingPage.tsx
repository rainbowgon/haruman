import React, { useEffect, useState } from "react";
import BubbleChart from "../components/BubbleChart";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";
import InfoItem from "../components/InfoItem";
import Register from "../components/RegistButton";
import axios from "axios";

//interface value
import { ChallengeState, User } from "../constants/interfaces";

// styles
import "../styles/RankinPageStyle.scss";
import BottomBarSpace from "../components/BottomBarSpace";
import { API_URL } from "../constants/urls";
import HeaderTitle from "../components/HeaderTitle";

const selectChallengeUserList = async () => {
  console.log("selectChallengeUserList");
  // 테스트용
  const accessToken = process.env.REACT_APP_accessToken;
  // 배포용
  // const accessToken = sessionStorage.getItem("accessToken");
  axios
    .get(`${API_URL}/api/challenges/people`, {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    })
    .then((response) => {
      console.log("일일 챌린지 현황판 리스트", response.data);
      console.log(response.data);
    })
    .catch((error) => {
      console.error("일일 챌린지 현황판 리스트", error);
    });
};
const RankingPage = () => {
  const [currentDate, setCurrentDate] = useState(new Date());
  const handleFailUser = () => {};
  const handleNotYetUser = async () => {
    // const zeroUsedUsers = await selectChallengeUserList();
    //   setSelectedUsers(zeroUsedUsers);
  };

  interface DataPoint {
    min: number;
    max: number;
    users: number;
    label: string;
    x?: number;
    y?: number;
  }

  // const [challengeitems, setChallengeitems] = useState<ChallengeItem[]>([]);
  const [Users, setUsers] = useState<User[]>([
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      usedAmount: 0,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      usedAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      usedAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      usedAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      usedAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      usedAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      usedAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      usedAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      usedAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      usedAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      usedAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      usedAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      usedAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      usedAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      usedAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      usedAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      usedAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      usedAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      usedAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      usedAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      usedAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      usedAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      usedAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      usedAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      usedAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      usedAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      usedAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      usedAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      usedAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      usedAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      usedAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      usedAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      usedAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      usedAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      usedAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      usedAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
  ]);

  const [selectedUsers, setSelectedUsers] = useState<User[]>([]);
  const [challengeInfo, setChallengeInfo] = useState<ChallengeState>({
    challengeId: -1,
    participantCount: 0,
    targetAmount: 0,
    usedAmount: 0,
    leftoverAmount: 0,
    challengeStatus: "null",
  });
  useEffect(() => {
    console.log("[useEffect] challengeInfo : ", challengeInfo);
  }, [challengeInfo]);

  const handleBubbleClick = (range: DataPoint) => {
    const usersInRange = Users.filter(
      (user) => user.usedAmount >= range.min && user.usedAmount <= range.max,
    );
    setSelectedUsers(usersInRange);
  };
  return (
    <CenterContainer>
      <MainStyle>
        {/* <BottomBarSpace /> */}
        <div className="rankingpage">
          <HeaderTitle
            SubTitle={`${
              challengeInfo && challengeInfo.participantCount
            }명의 유저가 먼저 진행하고 있어요!`}
            MainTitle={`${
              currentDate.getMonth() + 1
            }월 ${currentDate.getDate()}일 챌린지`}
          />
          <BubbleChart onBubbleClick={handleBubbleClick} />
          <div className="regular_container">
            <Register
              text="무지출 챌린지"
              className="regular brand"
              onClick={selectChallengeUserList}
            />
            <Register
              text="실패한 사람"
              className="regular white"
              onClick={handleFailUser}
            />
          </div>
          <div className="challengeitems_list">
            {selectedUsers.map((user, index) => (
              <InfoItem
                key={index}
                image={user.profileImage}
                mainValue={user.nickname}
                moneyValue={user.usedAmount}
              />
              // <div>{item.category}</div>
            ))}
          </div>
        </div>
        <BottomBarSpace />
      </MainStyle>
    </CenterContainer>
  );
};

export default RankingPage;
