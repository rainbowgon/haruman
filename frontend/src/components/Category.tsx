// import React from "react";

import "../styles/components/CategoryStyle.scss"

export interface CategoryProps {
    color : string;
    moneyValue : number;
    icon?: string;
    type?: string;
    checked?: boolean;
    className?: string;
  }
  
  export default function Category(
  {
    color = 'category_black_01',
    moneyValue,
    icon,
    type,
    checked,
    className,
  } : CategoryProps
  ) {
  return (
    <div className={`category ${color}`}>
      
    </div>
  );
};