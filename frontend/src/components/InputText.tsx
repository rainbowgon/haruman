import React from "react";
import "../styles/components/InputTextStyle.scss";


export interface InputProps {
  type: string;
  placeholder?: string;
  value: string;
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
  readonly?: boolean;
  checked?: boolean;
  style?: React.CSSProperties;
  className?: string;
}

export default function Input({
  type,
  placeholder,
  value,
  onChange,
  readonly,
  checked,
  style,
  className,
}: InputProps) {
  return (
    <div className="input_box">
      <div className="text_container">
        <div className="input_field_type">
          {placeholder}
        </div>
        <input
          className={`input_field ${className}`}
          type={type}
          placeholder={placeholder}
          value={value}
          onChange={onChange}
          readOnly={readonly}
          checked={checked}
          style={style}
        />
      </div>
    </div>
  );
}
