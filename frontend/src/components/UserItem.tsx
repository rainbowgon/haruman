// UserItem.tsx
import React from "react";

// style
import "../styles/components/UserItemStyle.scss";

export interface UserItemProps {
  mainValue: string;
  moneyValue: number;
  image?: string | null;
}

export default function UserItem({
  mainValue,
  moneyValue,
  image,
}: UserItemProps) {
  return (
    <div className="user_item">
      <div className="user_item_image_component">
        {(image) ? (
          <img
            src={image}
            alt="User Profile"
            className="user_item_image"
          />
        ) : null}
      </div>
      <div className="user_item_name_div">
        <p className="user_item_name">{mainValue}</p>
      </div>
      <div className="user_item_price_div">
        <p className="user_item_price">{moneyValue} 원</p>
      </div>
    </div>
  );
}
