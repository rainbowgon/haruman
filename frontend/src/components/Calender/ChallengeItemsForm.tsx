import React, { useState, useEffect } from "react";
import "../../styles/challenge/ChallengeItemsFormStyle.scss";
import { ChallengeDate } from "../../constants/interfaces";

interface ChallengeItemsProps {
  selectChallenge: ChallengeDate | undefined;
  dailyAmount: number | null;
}

const ChallengeItemsForm = ({
  selectChallenge,
  dailyAmount,
}: ChallengeItemsProps) => {
  const handleItemListDisplay = () => {
    if (selectChallenge === undefined) return "미진행";
    else {
      if (selectChallenge.status === "SUCCESS") {
        return "성공";
      } else if (selectChallenge.status === "FAIL") {
        return "실패";
      } else if (selectChallenge.status === "PROGRESS") {
        return "진행중";
      } else {
        return "미할당";
      }
    }
  };

  const collectItemValue = () => {
    if (selectChallenge === undefined) return 0;
    else {
      console.log(dailyAmount);
      return dailyAmount;
    }
  };

  return (
    <>
      {
        <div className="challenge_items_header">
          <div className="item_list_text_leftblock">
            <p className="item_list_text">사용금액</p>
          </div>
          <div className="item_list_text_rightblock">
            <div
              className={`item_list_display ${
                selectChallenge && selectChallenge.status
              }`}
            >
              <p className="item_list_display_text">
                {`${handleItemListDisplay()}`}
              </p>
            </div>
            <div className="item_price_block">
              <p className="item_list_text"> {`${collectItemValue()} 원`}</p>
            </div>
          </div>
        </div>
      }
    </>
  );
};

export default ChallengeItemsForm;
