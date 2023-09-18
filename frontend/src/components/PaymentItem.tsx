// AgreementContent.tsx
import React from "react";
import "../styles/user/AgreementContentSstyle.scss"

const PaymentItem = () => {
  const date = new Date();
  const year = date.getFullYear();
  const month = ("0" + (date.getMonth() + 1)).slice(-2);
  const day = ("0" + date.getDate()).slice(-2);
  const dateString = year + "-" + month + "-" + day;

  return (
    <div className="payment_item">
      <div className="payment_item_category">
        <img className="payment_item_category_img" src="" alt=""></img>
      </div>
      <div className="payment_item_name_div">
        <p className="payment_item_name">{}</p>
      </div>
      <div className="payment_item_price_div">
        <p className="payment_item_price"> {} 원</p>
      </div>
    </div>
  );
};

export default PaymentItem;
