// AgreementContent.tsx
import React from "react";

// style
import "../styles/components/InfoItemStyle.scss"

// icons
import Airplane     from "../assets/icons/consumptions/icon-airplane.svg";
import Animalpaw    from "../assets/icons/consumptions/icon-animalpaw.svg";
import Apartment    from "../assets/icons/consumptions/icon-apartment.svg";
import Barbershop   from "../assets/icons/consumptions/icon-barbershop.svg";
import Barcandy     from "../assets/icons/consumptions/icon-barcandy.svg";
import Beer         from "../assets/icons/consumptions/icon-beer.svg";
import Book         from "../assets/icons/consumptions/icon-book.svg";
import Cafe         from "../assets/icons/consumptions/icon-cafe.svg";
import Candy        from "../assets/icons/consumptions/icon-candy.svg";
import Car          from "../assets/icons/consumptions/icon-car.svg";
import Carrot       from "../assets/icons/consumptions/icon-carrot.svg";
import Child        from "../assets/icons/consumptions/icon-child.svg";
import Croissant    from "../assets/icons/consumptions/icon-croissant.svg";
import Cycle        from "../assets/icons/consumptions/icon-cycle.svg";
import Fastfood     from "../assets/icons/consumptions/icon-fastfood.svg";
import Gift         from "../assets/icons/consumptions/icon-gift.svg";
import Lipstick     from "../assets/icons/consumptions/icon-lipstick.svg";
import Medicine     from "../assets/icons/consumptions/icon-medicine.svg";
import Piano        from "../assets/icons/consumptions/icon-piano.svg";
import Popcorn      from "../assets/icons/consumptions/icon-popcorn.svg";
import Question     from "../assets/icons/consumptions/icon-question.svg";
import Restaurant   from "../assets/icons/consumptions/icon-restaurant.svg";
import Shopping     from "../assets/icons/consumptions/icon-shopping.svg";
import Taxi         from "../assets/icons/consumptions/icon-taxi.svg";
import Train        from "../assets/icons/consumptions/icon-train.svg";
import Won          from "../assets/icons/consumptions/icon-won.svg";


export interface InfoItemProps {
  mainValue : string;
  moneyValue : number;
  icon?: string;
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
    icon,
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
    <div className="payment_item">
      <div className="payment_item_category">
        <img className="payment_item_category_img" src={Won} alt={icon}></img>
      </div>
      <div className="payment_item_name_div">
        <p className="payment_item_name">{mainValue}</p>
      </div>
      <div className="payment_item_price_div">
        <p className="payment_item_price"> {moneyValue} 원</p>
      </div>
    </div>
  );
}