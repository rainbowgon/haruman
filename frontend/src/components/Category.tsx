// import React from "react";

import "../styles/components/CategoryStyle.scss"

export interface CategoryProps {
    color? : string;
    name? : string;
    type?: string;
    onClick?: (event: any) => void;
    checked?: boolean;
    className?: string;
  }
  
  export default function Category(
  {
    color = 'BLACK_01',
    name,
    type,
    checked,
    className,
  } : CategoryProps
  ) {
  return (
    <div className={`category ${className} ${color}`}>
      {name}
    </div>
  );
};