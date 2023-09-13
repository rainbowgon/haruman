import React from "react";

import styled from "styled-components";

const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const RegisterButton = styled("button")`
  width: 30vw;
  height: 60px;
  border-radius: 30px;
  font-size: 20px;
  padding: 8px;
  margin-bottom: 16px;
  max-width: 467px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 2px solid var(--brand2_main);
  color: var(--white);
  background-color: var(--brand2_main);
  font-weight: bolder;

  &:hover {
    transition: all 0.1s ease-in-out;
  }

  @media (max-width: 768px) {
    width: 80%;
  }
`;

export interface ButtonProps {
  text: string;
  onClick: (event: any) => void;
  className?: string;
  style?: React.CSSProperties;
}

export default function Register({ text, onClick, style }: ButtonProps) {
  return (
    <ButtonContainer>
      <RegisterButton
        style={style}
        onClick={onClick}
      >
        {text}
      </RegisterButton>
    </ButtonContainer>
  );
}
