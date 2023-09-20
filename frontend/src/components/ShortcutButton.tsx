// react
import React from "react";

// scss
import "../styles/components/ShortcutButtonStyle.scss";


export interface ButtonProps {
  text: string;
  onClick: (event: any) => void;
  className?: string;
  style?: React.CSSProperties;
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
          <img src="" alt="로고"></img>
          {text}
        </button>
      </div>
    </div>
  );
}