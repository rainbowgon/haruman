import React, { useState, useEffect } from "react";
import "../../styles/calendar/ChallengeItemsFormStyle.scss"

const ChallengeItemsForm = () => {

  return (
  <>
    <div className="challenge_items_header">
      <div className="item_list_text_leftblock">
        <p className="item_list_text">사용금액</p>
      </div>
      <div className="item_list_text_rightblock">
        <div className="item_list_display">
          <p className="item_list_display_text">{}성공</p>
        </div>
        <div className="item_price_block">
          <p className="item_list_text">{} 원</p>
        </div>
      </div>
    </div>
    <div className="challenge_items_list">
        
    </div>
  </>
  )
}

export default ChallengeItemsForm;