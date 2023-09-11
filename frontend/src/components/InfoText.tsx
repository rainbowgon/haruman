import React, { useContext } from "react";

import styled from "styled-components";

const TextContainer = styled.div`
  display: flex;
  justify-content: center;
`;

const InputField = styled.span`
  width: 58%;
  height: 30px;
  font-size: 16px;
  margin: 4px;
  margin-bottom: 12px;
  font-weight: bolder;
  text-align: center;
`;

interface InputProps {
  text: string;
}

export default function Info({ text }: InputProps) {
  return (
    <TextContainer>
      <InputField>{text}</InputField>
    </TextContainer>
  );
}
