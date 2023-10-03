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
  
  /**
    updateExpense   
    지출 내역 수정   
    /challenges   
    PATCH
  */
    const updateExpense = () => {
      // const accessToken = sessionStorage.getItem("accessToken")
      // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);
  
      // axios.patch(`${API_URL}${contextPath}${ChallengeAPI}`, "edit",
      // {
      //   headers: {
      //     Authorization: `Bearer ${accessToken}`
      //   }
      // })
      // .then((response) => {
      //     console.log("지출 내역 수정");
      //     showAlert("success", "지출 내역 수정");
      //     setCurStatus(2);
      // })
      // .catch((error) => {
      //     console.error("서버로부터 지출 내역 수정 실패", error);
      //     showAlert("error", "지출 내역 수정 실패입니다.");
      //     console.error(error.code);
      // });
    }

  /**
    deleteExpense   
    지출 내역 삭제   
    /challenges/{expense-id}   
    DELETE
  */
  const deleteExpense = () => {
    // const accessToken = sessionStorage.getItem("accessToken")
    // const expenseId = e.target;

    // axios.delete(`${API_URL}${contextPath}${ChallengeAPI}/{expense-id}`,
    // {
    //   headers: {
    //     Authorization: `Bearer ${accessToken}`
    //   }
    // })
    // .then((response) => {
    //   showAlert("success", "지출 내역 삭제 요청 취소 성공입니다.");
    // })
    // .catch((error) => {
    //   console.error("서버로부터 지출 내역 삭제 요청 실패", error);
    //   showAlert("error", "지출 내역 삭제 요청 실패입니다.");
    //   console.error(error.code);
    // });
  }

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