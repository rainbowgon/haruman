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

//icon
import Check from "../assets/icons/icon-check.png";

//scss
import "../styles/components/CheckBoxStyle.scss"

interface CheckboxProps {
  label: string;
  checked: boolean;
  onChange: (event: any) => void;
}

export default function CheckBox({ label, checked, onChange }: CheckboxProps) {
  return (
    <div className="check_box_guide">
      <div className="check_box">
        <label className="check_box_div">
          <input className="checkbox_input"
            type="checkbox"
            checked={checked}
            onChange={onChange}
          />
          <label className="checkbox_label">{label}</label>
        </label>
      </div>
    </div>
  );
}
