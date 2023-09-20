// AgreementContent.tsx
import React from "react";

// style
import "../styles/components/InfoItemStyle.scss"

export interface InfoItemProps {
  mainValue : string;
  moneyValue : number;
  image?: string;
  type?: string;
  placeholder?: string;
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
  checked?: boolean;
  className?: string;
}

export default function InfoItem(
  {
    mainValue,
    moneyValue,
    image,
    type,
    placeholder,
    onChange,
    checked,
    className,
  } : InfoItemProps
  ) {
  const date = new Date();
  const year = date.getFullYear();
  const month = ("0" + (date.getMonth() + 1)).slice(-2);
  const day = ("0" + date.getDate()).slice(-2);
  const dateString = year + "-" + month + "-" + day;

  return (
    <div className="info_item">
      <div className="info_item_category">
        <img className="info_item_category_img" src={image} alt="user_IMG"></img>
      </div>
      <div className="info_item_name_div">
        <p className="info_item_name">{mainValue}</p>
      </div>
      <div className="info_item_price_div">
        <p className="info_item_price"> {moneyValue} 원</p>
      </div>
    </div>
  );
}