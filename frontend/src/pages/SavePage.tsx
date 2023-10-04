import { useEffect, useState } from "react";

// urls
import { API_URL } from "../constants/urls";

// image
import Logo from "../assets/logo-mainlogo.svg"

// style
import "../styles/save/SavePageStyle.scss"
import { saveItem } from "../constants/interfaces";
import axios from "axios";
import SavepageItem from "../components/Save/SavepageItem";
import SavepageProposeItem from "../components/Save/SavepageProposeItem";


const SavePage = () => {
  // 테스트용
  const accessToken = process.env.REACT_APP_accessToken;
  
  // 배포용
  // const accessToken = sessionStorage.getItem("accessToken");

  const contextPath = `/api`;
  const ChallengeAPI = '/challenges';

  const [amount, setAmount] = useState(0);

  const [saveItems, setSaveItems] = useState<saveItem[]>([
    {
      "bank": "신한은행",
      "name": "My365적금",
      "description": "기가막힌 적금",
      "interestRate": 4.5
    },
    {
      "bank": "국민은행",
      "name": "KB적금",
      "description": "코가 뚫리는 적금",
      "interestRate": 4.3
    }
  ]);

  useEffect(() => {
    selectAccumulatedAmount();
  }, [])

  /**
   * selectAccumulatedAmount
   * 회원의 챌린지 누적 잔액 조회
   * /challenges/amount
   * GET
   */
  const selectAccumulatedAmount = () => {
    axios.get(`${API_URL}${contextPath}${ChallengeAPI}/amount`,
      {
        headers: {
          Authorization: `Bearer ${accessToken}`
        }
      })
      .then((response) => {
        console.log("누적 잔액 조회 성공", response.data.data.accumulatedAmount);
        setAmount(response.data.data.accumulatedAmount);
      })
      .catch((error) => {
        console.error('누적 잔액 조회 실패', error);
      });
  }

  return(
    <div className="savepage_container">
      <div className="savepage_header">
        <img className="savepage_header_logo" src={Logo} alt="로고"/>
      </div>
      <div className="savepage_save_container">
        <h2 className="savepage_title">여태 모은 {amount}원을 저축해요!</h2>
        <div className="savepage_itemlist">
          {
            saveItems &&
            saveItems.map((item) => (
              <SavepageItem
                imgName = {item.bank}
                mainText = {item.name}
                subText = {item.description}
                interest = {item.interestRate}
              />
            ))
          }
        </div>
      </div>
      <div className="savepage_compare_container">
        <h2 className="savepage_title">지금까지 이만큼 아꼈어요!</h2>
        <div className="savepage_itemlist">
          {amount && Array(3).fill(null).map((_) => (
            <SavepageProposeItem 
              amount={amount} 
            />
          ))}
        </div>
      </div>
    </div>
  )
};

export default SavePage;