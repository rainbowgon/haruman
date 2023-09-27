// react
import React from "react";

// scss
import "../styles/components/ShortcutButtonStyle.scss";

// icons
import HomeIcon from "../assets/icons/icon-home.svg";
import ChartsetIcon from "../assets/icons/icon-chartset.svg";
import CalendarIcon from "../assets/icons/icon-calendarday.svg";
import PiggybankIcon from "../assets/icons/icon-piggybank.svg";
import PlusIcon from "../assets/icons/icon-plus.svg";


export interface ButtonProps {
  text: string;
  onClick: (event: any) => void;
  className?: string;
  style?: React.CSSProperties;
}

function handleSelectIcon(textIcon:string) {
  if (textIcon === "HomePage"){
    return HomeIcon;
  }
  else if (textIcon === "TodayExpensePage"){
    return CalendarIcon;
  }
  else if (textIcon === "CalendarPage"){
    return CalendarIcon;
  }
  else if (textIcon === "RankingPage"){
    return ChartsetIcon;
  }
  else if (textIcon === "PiggybankIcon"){
    return PiggybankIcon;
  }
  else {
    return PlusIcon;
  }
}

function handleSelectText(text:string) {
  if (text === "HomePage"){
    return "홈";
  }
  else if (text === "TodayExpensePage"){
    return "투데이";
  }
  else if (text === "CalendarPage"){
    return "홈";
  }
  else if (text === "RankingPage"){
    return "랭킹";
  }
  else if (text === "PiggybankIcon"){
    return "추천";
  }
  else {
    return "추가기능";
  }
}

export default function ShortcutButton({ text, onClick, className, style }: ButtonProps) {
  return (
    <div className="shortcut_button_box">
      <div className={`shortcut_button_container`}>
        <button
          className={`shortcut_button ${className}`}
          style={style}
          onClick={onClick}
        >
          <img className="shortcut_button_icon" src={handleSelectIcon(text)} alt="로고"></img>
          <p className="shortcut_button_text">{handleSelectText(text)}</p>
        </button>
      </div>
    </div>
  );
}