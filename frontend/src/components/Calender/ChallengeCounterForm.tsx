import React, { useState, useEffect } from "react";
import "../../styles/calendar/ChallengeCounterFormStyle.scss"

const ChallengeCounterForm = () => {
// const attend = useSelector((state) => state.userState.user?.accessList)
// const userId = useSelector((state) => state.userState.user?.userId)

return (
<>
  <div className="challengecounter">
    <div className="challengecounter_box challengecounter_item">
      <div className="color_pointer current"/>
      <div className="challengecounter_counter">11{}</div>
      <div className="challengecounter_text">도전</div>
    </div>

    <div className="challengecounter_box styled_Challenge_bolck">
      <div className="styled_horizontal_line"/>
    </div>
    
    <div className="challengecounter_box challengecounter_item">
      <div className="color_pointer success"/>
      <div className="challengecounter_counter">8{}</div>
      <div className="challengecounter_text">성공</div>
    </div>

    <div className="challengecounter_box styled_Challenge_bolck">
      <div className="styled_horizontal_line"/>
    </div>

    <div className="challengecounter_box challengecounter_item">
      <div className="color_pointer fail"/>
      <div className="challengecounter_counter">2{}</div>
      <div className="challengecounter_text">실패</div>
    </div>
  </div>
</>
)
}

export default ChallengeCounterForm;