// AgreementContent.tsx
import React from "react";

// style
import "../styles/components/SpentItemStyle.scss"
import Category from "./Category";

export interface SpentItemProps {
  mainValue : string;
  moneyValue : number;
  name? : string;
  color? : string;
  type?: string;
  placeholder?: string;
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
  checked?: boolean;
  className?: string;
}

export default function SpentItem(
  {
    mainValue,
    moneyValue,
    name,
    color,
    type,
    placeholder,
    onChange,
    checked,
    className,
  } : SpentItemProps
  ) {
  const date = new Date();
  return (
    <div className="spent_item">
      <div className="spent_item_name_div">
        <p className="spent_item_name">{mainValue}</p>
        <Category
          color={color}
          name={name}
        />
      </div>
      <div className="spent_item_price_div">
        <p className="spent_item_price"> {moneyValue} 원</p>
      </div>
    </div>
  );
}