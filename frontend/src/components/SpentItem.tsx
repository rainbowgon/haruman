// AgreementContent.tsx
import React from "react";
import axios from "axios";
import Swal from "sweetalert2";

// style
import "../styles/components/SpentItemStyle.scss";
import Category from "./Category";

// apis
import { API_URL } from "../constants/urls";
const contextPath = `/api`;
const ChallengeAPI = "/challenges";

export interface SpentItemProps {
  mainValue: string;
  moneyValue: number;
  name?: string;
  color?: string;
  type?: string;
  placeholder?: string;
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
  id?: number;
  className?: string;
}

export default function SpentItem({
  mainValue,
  moneyValue,
  name,
  color,
  type,
  placeholder,
  onChange,
  id,
  className,
}: SpentItemProps) {
  // 테스트용
  // const accessToken = process.env.REACT_APP_accessToken;
  // 배포용
  const accessToken = sessionStorage.getItem("accessToken");

  /**
    updateExpense   
    지출 내역 수정   
    /challenges   
    PATCH
  */
  const updateExpense = () => {
    const accessToken = sessionStorage.getItem("accessToken");
    axios
      .patch(`${API_URL}${contextPath}${ChallengeAPI}`, "edit", {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        console.log("지출 내역 수정");
        showAlert("지출 내역 수정");
      })
      .catch((error) => {
        console.error("서버로부터 지출 내역 수정 실패", error);
        showAlert("지출 내역 수정 실패입니다.");
        console.error(error.code);
      });
  };

  /**
    deleteExpense   
    지출 내역 삭제   
    /challenges/{expense-id}   
    DELETE
  */
  const deleteExpense = () => {
    axios
      .delete(`${API_URL}${contextPath}${ChallengeAPI}/${id}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        showAlert(`지출 내역 삭제 요청 성공입니다. ${response.data}`);
      })
      .catch((error) => {
        console.error("서버로부터 지출 내역 삭제 요청 실패", error);
        showAlert("지출 내역 삭제 요청 실패입니다.");
        console.error(error.code);
      });
  };

  const showAlert = (text: string) => {
    Swal.fire({
      text,
    });
  };

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
      <div></div>
    </div>
  );
}
