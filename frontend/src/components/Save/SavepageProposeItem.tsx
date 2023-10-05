import { useEffect, useState } from "react";

// styles
import "../../styles/save/SavepageProposeItemStyle.scss"

// icons
import Airplane from "../../assets/icons/consumptions/icon-airplane.svg"
import Apartment from "../../assets/icons/consumptions/icon-apartment.svg"
import Barbershop from "../../assets/icons/consumptions/icon-barbershop.svg"
import Beer from "../../assets/icons/consumptions/icon-beer.svg"
import Book from "../../assets/icons/consumptions/icon-book.svg"
import Cafe from "../../assets/icons/consumptions/icon-cafe.svg"
import Car from "../../assets/icons/consumptions/icon-car.svg"
import Croissant from "../../assets/icons/consumptions/icon-croissant.svg"
import Cycle from "../../assets/icons/consumptions/icon-cycle.svg"
import Fastfood from "../../assets/icons/consumptions/icon-fastfood.svg"
import Lipstick from "../../assets/icons/consumptions/icon-lipstick.svg"
import Piano from "../../assets/icons/consumptions/icon-piano.svg"
import Popcorn from "../../assets/icons/consumptions/icon-popcorn.svg"
import Restaurant from "../../assets/icons/consumptions/icon-restaurant.svg"
import Shopping from "../../assets/icons/consumptions/icon-shopping.svg"
import Texi from "../../assets/icons/consumptions/icon-taxi.svg"
import Train from "../../assets/icons/consumptions/icon-train.svg"

export interface SavepageProposeItemProps {
  onClick?: (event: any) => void;
  amount: number;
  count: number;
  className?: string;
}

const SavepageProposeItem = ({
  onClick,
  amount,
  count,
  className,
} : SavepageProposeItemProps) => {
  const [randomItem, setRandomItem] = useState({
    name: "비행기",
    count: 0,
    content: `번 탈 수 있어요!`,
  });

  useEffect(() => {
    const randomValue : number = Math.random() * (amount/5000);
    // const randomValue : number = count;

    console.log(randomValue, amount);

    if (randomValue < 1){
      setRandomItem({
        name: "빵",
        count: amount/4000,
        content: `개를 살 수 있어요!`,
      });
      return;
    } else if (randomValue<2) {
      setRandomItem({
        name: "밥",
        count: amount/10000,
        content: `끼 먹을 수 있어요!`,
      });
      return;
    } else if (randomValue<3) {
      setRandomItem({
        name: "영화",
        count: amount/15000,
        content: `번 볼 수 있어요!`,
      });
      return;
    } else if (randomValue<4) {
      setRandomItem({
        name: "헤어샵",
        count: amount/20000,
        content: `번 갈 수 있어요!`,
      });
      return;
    } else if (randomValue<5) {
      setRandomItem({
        name: "책",
        count: amount/25000,
        content: `권 살 수 있어요!`,
      });
      return;
    } else if (randomValue<6) {
      setRandomItem({
        name: "머그컵",
        count: amount/30000,
        content: `살 수 있어요!`,
      });
      return;
    } else if (randomValue<7) {
      setRandomItem({
        name: "화장품",
        count: amount/35000,
        content: `개 살 수 있어요!`,
      });
      return;
    } else if (randomValue<8) {
      setRandomItem({
        name: "쇼핑",
        count: amount/40000,
        content: `번 할 수 있어요!`,
      });
      return;
    } else if (randomValue<10) {
      setRandomItem({
        name: "비행기",
        count: amount/50000,
        content: `번 탈 수 있어요!`,
      });
      return;
    } else if (randomValue<12) {
      setRandomItem({
        name: "기차여행",
        count: amount/60000,
        content: `번 할 수 있어요!`,
      });
      return;
    } else if (randomValue<60) {
      setRandomItem({
        name: "자전거",
        count: amount/300000,
        content: `개를 살 수 있어요!`,
      });
      return;
    } else if (randomValue<120) {
      setRandomItem({
        name: "피아노",
        count: amount/600000,
        content: `개를 살 수 있어요!`,
      });
      return;
    } else {
      setRandomItem({
        name: "아파트",
        count: amount/500000000,
        content: `채를 살 수 있어요!`,
      });
      return;
    }
  }, [])

  const handleName = () => {
    return "Airplane";
  }
  
  const handleImage = () => {
    if(randomItem.name === "빵"){
      return Croissant;
    } else if (randomItem.name === "비행기") {
      return Airplane;
    } else if (randomItem.name === "아파트") {
      return Apartment;
    } else if (randomItem.name === "책") {
      return Book;
    } else if (randomItem.name === "영화") {
      return Popcorn;
    } else if (randomItem.name === "밥") {
      return Restaurant;
    } else if (randomItem.name === "헤어샵") {
      return Barbershop;
    } else if (randomItem.name === "기차여행") {
      return Train;
    } else if (randomItem.name === "피아노") {
      return Piano;
    } else if (randomItem.name === "자전거") {
      return Cycle;
    } else if (randomItem.name === "화장품") {
      return Lipstick;
    } else if (randomItem.name === "머그컵") {
      return Cafe;
    } else if (randomItem.name === "쇼핑") {
      return Shopping;
    } 
    
    // return Beer;
    // return Car;
    // return Fastfood;
    // return Texi;
  }

  return (
    <div className="save_propose_item_component">
      <div className="save_propose_item_img_circle">
        {
          <img className={`save_propose_item_img`} src={handleImage()} alt="아이콘"/>
        }
      </div>
      <div className="save_propose_item_contents">
        <p className="save_propose_item_maintext">
          {randomItem.name}
          <b className="save_propose_item_highlight">{randomItem.count.toFixed(1)}</b>
          {randomItem.content}</p>
      </div>
    </div>
  );
};

export default SavepageProposeItem;
