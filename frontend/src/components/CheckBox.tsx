// import React from "react";
// import styled from "styled-components";
// import "../styles/theme.css";

// export interface CheckboxProps {
//   label: string;
//   checked: boolean;
//   onChange: (isChecked: boolean) => void;
// }

// const CheckBoxButton = styled.input`
//   border: 2px solid var(--brand1_main);
//   color: var(--white);
//   background-color: var(--brand1_main);
// `;

// export default function Checkbox({ label, checked, onChange }: CheckboxProps) {
//   const handleChange = () => {
//     onChange(!checked);
//   };

//   return (
//     <label>
//       <CheckBoxButton
//         type="checkbox"
//         checked={checked}
//         onChange={handleChange}
//       />
//       {label}
//     </label>
//   );
// }

import React, { useContext } from "react";

import styled from "styled-components";

interface CheckboxProps {
  label: string;
  checked: boolean;
  onChange: (event: any) => void;
}

const CheckboxContainer = styled.label`
  display: flex;
  justify-content: center;
  cursor: pointer;
`;

const CheckboxInput = styled.input`
  appearance: none;
  width: 18px;
  height: 18px;
  border-radius: 4px;
  margin: 4px;
  margin-bottom: 12px;
  cursor: pointer;
  border: 1px solid var(--brand2_main);
  background-color: ${(props) => (props.checked ? "var(--brand2_main)" : "")};
  color: var(--brand2_main);

  &:checked {
    background: var(--brand2_main);
    transition: all 0.1s ease-in-out;
  }
`;

const CheckboxLabel = styled.span`
  font-size: 20px;
  margin: 4px;
  margin-bottom: 12px;
`;

export default function CheckBox({ label, checked, onChange }: CheckboxProps) {
  return (
    <CheckboxContainer>
      <CheckboxInput
        type="checkbox"
        checked={checked}
        onChange={onChange}
      />
      <CheckboxLabel>{label}</CheckboxLabel>
    </CheckboxContainer>
  );
}
