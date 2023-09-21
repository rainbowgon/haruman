// import React from "react";

import "../styles/components/CategoryStyle.scss"

export interface CategoryProps {
    color? : string;
    category? : string;
    type?: string;
    checked?: boolean;
    className?: string;
  }
  
  export default function Category(
  {
    color = 'BLACK_01',
    category,
    type,
    checked,
    className,
  } : CategoryProps
  ) {
  return (
    <div className={`category ${className} ${color}`}>
      {category}
    </div>
  );
};