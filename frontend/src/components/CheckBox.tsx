// import React, { useState } from "react";
// import "../styles/theme.css";

// interface CheckboxProps {
//   label: string;
//   checked: boolean;
//   onChange: (isChecked: boolean) => void;
// }

// const Checkbox: React.FC<CheckboxProps> = ({ label }) => {
//   const [checked, setChecked] = useState(false);

//   const handleChange = () => {
//     setChecked(!checked);
//   };

//   return (
//     <div>
//       <label>
//         <input
//           type="checkbox"
//           checked={checked}
//           onChange={handleChange}
//         />
//         {label}
//       </label>
//     </div>
//   );
// };

// export default Checkbox;

import React from "react";
import styled from "styled-components";
import "../styles/theme.css";

export interface CheckboxProps {
  label: string;
  checked: boolean;
  onChange: (isChecked: boolean) => void;
}

const CheckBoxButton = styled.input`
  border: 2px solid var(--brand1_main);
  color: var(--white);
  background-color: var(--brand1_main);
`;

export default function Checkbox({ label, checked, onChange }: CheckboxProps) {
  const handleChange = () => {
    onChange(!checked);
  };

  return (
    <label>
      <CheckBoxButton
        type="checkbox"
        checked={checked}
        onChange={handleChange}
      />
      {label}
    </label>
  );
}
