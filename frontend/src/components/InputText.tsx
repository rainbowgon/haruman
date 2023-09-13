import React from "react";
import "../styles/theme.css";

import styled from "styled-components";

const TextContainer = styled.div`
  display: flex;
  justify-content: center;
`;

const InputField = styled.input`
  width: 29vw;
  height: 6vh;
  border-radius: 40px;
  font-size: 1.25vw;
  padding: 8px;
  margin-bottom: 20px;
  max-width: 450px;
  color: var(--white);
  border: 2px solid var(--brand2_main);

  text-align: center;

  &::placeholder {
    text-align: center;
  }

  @media (max-width: 768px) {
    width: 80%;
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
      />
    </TextContainer>
  );
}
