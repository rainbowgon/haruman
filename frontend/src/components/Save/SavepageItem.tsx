// AgreementContent.tsx
import "../../styles/save/SavepageItemStyle.scss"

// images
import Shinhan from "../../assets/image/bank/image_shinhan.svg"
import Kb from "../../assets/image/bank/image_kb.svg"
import Hana from "../../assets/image/bank/image_hana.svg"
import Woori from "../../assets/image/bank/image_woori.svg"
import Kakao from "../../assets/image/bank/image_kakao.svg"
import Toss from "../../assets/image/bank/image_toss.svg" 
import Question from "../../assets/icons/consumptions/icon-question.svg"
import { useNavigate } from "react-router-dom"

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
    if (imgName){
      if (imgName === "신한은행"){
        window.location.href = 'https://m.shinhan.com/rib/mnew/index.jsp#220011110001';
        return "shinhan";
      } else if(imgName === "국민은행") {
        window.location.href = 'https://obank.kbstar.com/quics?page=C020702';
        return "kb";
      } else if(imgName === "하나은행") {
        window.location.href = 'https://www.kebhana.com/cont/mall/mall09/mall0902/mall090201/index.jsp';
        return "hana";
      } else if(imgName === "우리은행") {
        window.location.href = 'https://spot.wooribank.com/pot/Dream?withyou=PODEP0021';
        return "woori";
      } else if(imgName === "카카오뱅크") {
        window.location.href = 'https://www.kakaobank.com/products/savings';
        return "kakao";
      } else if(imgName === "토스뱅크") {
        window.location.href = 'https://www.tossbank.com/product-service/savings/savings-gulbi';
        return "toss";
      } else {
  
      }
    }
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
    } else if(img === "카카오뱅크") {
      return "kakao";
    } else if(img === "토스뱅크") {
      return "toss";
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
    } else if(img === "카카오뱅크") {
        return Kakao;
    } else if(img === "토스뱅크") {
        return Toss;
    } else {
        return Question;
    }
  }
  }

  return (
    <div className="save_item_component" onClick={handleItemComponent}>
      <div className="save_item_img_circle">
        {
          <img className={`save_item_img`} src={handleImage(imgName)} alt="아이콘"/>
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
