import React, { useState } from "react";
import BubbleChart from "../components/BubbleChart";
import CenterContainer from "../components/CenterContainer";
import MainStyle from "../components/MainStyle";
import InfoItem from "../components/InfoItem";

//interface value
import { User } from "../constants/interfaces";

// styles
import "../styles/RankinPageStyle.scss";

const RankingPage = () => {
  // interface BalanceData {
  //   balance: number;
  // }

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
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
  ]);

  const [selectedUsers, setSelectedUsers] = useState<User[]>([]);

  const handleBubbleClick = (range: DataPoint) => {
    const usersInRange = Users.filter(
      (user) =>
        user.leftoverAmount >= range.min && user.leftoverAmount <= range.max,
    );
    setSelectedUsers(usersInRange);
  };
  return (
    <CenterContainer>
      <MainStyle>
        <div className="rankingpage">
          <div className="ranking_header">
            <h3 className="sub_title">[]명이 도전중!</h3>
            <h1 className="main_title">[]월 []일 챌린지</h1>
          </div>
          <BubbleChart onBubbleClick={handleBubbleClick} />

          <div className="challengeitems_list">
            {selectedUsers.map((user, index) => (
              <InfoItem
                key={index}
                image={user.profileImage}
                mainValue={user.nickname}
                moneyValue={user.leftoverAmount}
              />
              // <div>{item.category}</div>
            ))}
          </div>
        </div>
      </MainStyle>
    </CenterContainer>
  );
};

export default RankingPage;
