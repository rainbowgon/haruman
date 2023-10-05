// react
import React from "react";

// scss
import "../styles/components/RegistButtonStyle.scss";

export interface ButtonProps {
  text: string;
  onClick: (event: any) => void;
  className?: string;
  style?: React.CSSProperties;
}

export default function Register({
  text,
  onClick,
  className,
  style,
}: ButtonProps) {
  return (
    <div className="regist_button_box">
      <div className="button_container">
        <button
          className={`register_button ${className}`}
          style={style}
          onClick={onClick}
        >
          {text}
        </button>
      </div>
    </div>
  );
}
