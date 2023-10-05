// react
import React from "react";
import { useNavigate } from "react-router-dom";
// scss
import "../styles/components/ShortcutButtonStyle.scss";

// icons
import HomeIcon from "../assets/icons/icon-home.svg";
import ChartsetIcon from "../assets/icons/icon-chartset.svg";
import CalendarIcon from "../assets/icons/icon-calendarday.svg";
import PiggybankIcon from "../assets/icons/icon-piggybank.svg";
import PlusIcon from "../assets/icons/icon-plus.svg";
import axios from "axios";
import { API_URL } from "../constants/urls";

export interface ButtonProps {
  text: string;
  onClick: (event: any) => void;
  className?: string;
  style?: React.CSSProperties;
}

function handleSelectIcon(textIcon: string) {
  if (textIcon === "HomePage") {
    return HomeIcon;
  } else if (textIcon === "TodayExpensePage") {
    return CalendarIcon;
  } else if (textIcon === "CalendarPage") {
    return CalendarIcon;
  } else if (textIcon === "RankingPage") {
    return ChartsetIcon;
  } else if (textIcon === "PiggybankIcon") {
    return PiggybankIcon;
  } else {
    return PlusIcon;
  }
}

function handleSelectText(text: string) {
  if (text === "HomePage") {
    return "홈";
  } else if (text === "TodayExpensePage") {
    return "투데이";
  } else if (text === "CalendarPage") {
    return "홈";
  } else if (text === "RankingPage") {
    return "랭킹";
  } else if (text === "PiggybankIcon") {
    return "추천";
  } else {
    return "추가기능";
  }
}

export default function ShortcutButton({
  text,
  onClick,
  className,
  style,
}: ButtonProps) {
  const navigate = useNavigate();

  const handleShortcut = () => {
    if (text === "HomePage") {
      navigate("/home");
      return;
    } else if (text === "TodayExpensePage") {
      navigate("/today");
      return;
    } else if (text === "CalendarPage") {
      navigate("/calendar");
      return;
    } else if (text === "RankingPage") {
      navigate("/ranking");
      return;
    } else if (text === "PiggybankIcon") {
      navigate("/save");
      return;
    } else {
      
      const userData = {
        nickname: '테스트',
      };

      axios
      .post(`${API_URL}/api/members`, userData, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .then((response) => {
        console.log(
          "토큰제작 성공",
          response,
        );
      })
      .catch((error) => {
        console.error("토큰제작 실패", error);
      });
      return;
    }
  };
  return (
    <div className="shortcut_button_box">
      <div className={`shortcut_button_container`}>
        <button
          className={`shortcut_button ${className}`}
          style={style}
          onClick={handleShortcut}
        >
          <img
            className="shortcut_button_icon"
            src={handleSelectIcon(text)}
            alt="로고"
          ></img>
          <p className="shortcut_button_text">{handleSelectText(text)}</p>
        </button>
      </div>
    </div>
  );
}
