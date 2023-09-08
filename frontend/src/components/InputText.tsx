import React, { useContext } from "react";

import styled from "styled-components";

const TextContainer = styled.div`
  display: flex;
  justify-content: center;
`;

const InputField = styled.input`
  width: 29vw;
  height: 6vh;
  border-radius: 20px;
  font-size: 1.25vw;
  padding: 8px;
  margin-bottom: 20px;
  max-width: 450px;

  text-align: center;

  &::placeholder {
    text-align: center;
  }

  @media (max-width: 768px) {
    width: 80%; /* 또는 원하는 크기로 지정 */
  }
`;

interface InputProps {
  type: string;
  placeholder?: string;
  value: string;
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
  readonly?: boolean;
  checked?: boolean;
  style?: React.CSSProperties;
  tabIndex?: number;
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
  tabIndex,
  className,
}: InputProps) {
  return (
    <TextContainer>
      <InputField
        className={className}
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        readOnly={readonly}
        checked={checked}
        style={style}
        tabIndex={tabIndex}
      />
    </TextContainer>
  );
}
