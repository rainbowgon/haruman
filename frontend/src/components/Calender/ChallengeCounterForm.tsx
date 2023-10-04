import React, { useState, useEffect } from "react";
import "../../styles/challenge/ChallengeCounterFormStyle.scss";

interface ChallengeCounterProps {
  failCount: number;
  successCount: number;
}

const ChallengeCounterForm = ({
  failCount,
  successCount,
}: ChallengeCounterProps) => {
  // const attend = useSelector((state) => state.userState.user?.accessList)
  // const userId = useSelector((state) => state.userState.user?.userId)

  return (
    <>
      <div className="challengecounter">
        <div className="challengecounter_box challengecounter_item">
          <div className="color_pointer_div">
            <div className="color_pointer current" />
          </div>
          <div className="challengecounter_counter">
            {failCount + successCount}
          </div>
          <div className="challengecounter_text">도전</div>
        </div>

        <div className="challengecounter_box styled_Challenge_bolck">
          <div className="styled_horizontal_line" />
        </div>

        <div className="challengecounter_box challengecounter_item">
          <div className="color_pointer_div">
            <div className="color_pointer success" />
          </div>
          <div className="challengecounter_counter">{successCount}</div>
          <div className="challengecounter_text">성공</div>
        </div>

        <div className="challengecounter_box styled_Challenge_bolck">
          <div className="styled_horizontal_line" />
        </div>

        <div className="challengecounter_box challengecounter_item">
          <div className="color_pointer_div">
            <div className="color_pointer fail" />
          </div>
          <div className="challengecounter_counter">{failCount}</div>
          <div className="challengecounter_text">실패</div>
        </div>
      </div>
    </>
  );
};

export default ChallengeCounterForm;
