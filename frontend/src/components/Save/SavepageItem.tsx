// AgreementContent.tsx
import "../../styles/save/SavepageItemStyle.scss"

// icons
import Airplane from "../../assets/icons/consumptions/icon-airplane.svg"

// images
import Shinhan from "../../assets/image/bank/image_shinhan.svg"
import Kb from "../../assets/image/bank/image_kb.svg"
import Hana from "../../assets/image/bank/image_hana.svg"
import Woori from "../../assets/image/bank/image_woori.svg"
import Question from "../../assets/icons/consumptions/icon-question.svg"

export interface SavepageItemProps {
  imgName? : string;
  mainText : string;
  subText?: string;
  onClick?: (event: any) => void;
  interest?: number;
  className?: string;
}

const SavepageItem = ({
  imgName,
  mainText,
  subText,
  onClick,
  className,
  interest,
} : SavepageItemProps) => {

  // 페이지 확장 시 사용할 기능
  const handleItemComponent = () => {

  }

  const handleName = (img : string) => {
    if (img === "신한은행"){
      return "shinhan";
    } else if(img === "국민은행") {
        return "kb";
    } else if(img === "하나은행") {
        return "hana";
    } else if(img === "우리은행") {
        return "woori";
    } else {

    }
  }
  
  const handleImage = (img? : string) => {
  if (img) {
    if (img === "신한은행"){
        return Shinhan;
    } else if(img === "국민은행") {
        return Kb;
    } else if(img === "하나은행") {
        return Hana;
    } else if(img === "우리은행") {
        return Woori;
    } else {
        return Question;
    }
  }
  }

  return (
    <div className="save_item_component" onClick={handleItemComponent}>
      <div className="save_item_img_circle">
        {
          <img className={`save_item_img ${imgName && handleName(imgName)}`} src={handleImage(imgName)} alt="아이콘"/>
        }
      </div>
      <div className="save_item_contents">
        <p className="save_item_maintext">{mainText}</p>
        <p className="save_item_subtext">{subText}</p>
      </div>
      <div className="save_item_interest">
        <p className="save_item_interest_text">최대</p>
        <p className="save_item_interest_rate">{interest}%</p>
      </div>
    </div>
  );
};

export default SavepageItem;
