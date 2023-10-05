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
import BottomBarSpace from "../components/BottomBarSpace";


const SavePage = () => {
  // 테스트용
  // const accessToken = process.env.REACT_APP_accessToken;
  
  // 배포용
  const accessToken = sessionStorage.getItem("accessToken");

  const contextPath = `/api`;
  const ChallengeAPI = '/challenges';
  const DepositAPI = '/deposits';

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
    },
    {
      "bank": "하나은행",
      "name": "올 in 하나 적금",
      "description": "한 번에 모든 것을 하는 적금",
      "interestRate": 4.1
    },
    {
      "bank": "우리은행",
      "name": "우리함께 건강 적금",
      "description": "운동하면 이자율이 올라가는 적금",
      "interestRate": 4.9
    }
  ]);

  useEffect(() => {
    selectAccumulatedAmount();
    selectDepositDetailList();
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


  /**
   * selectDepositDetailList	
   * 적금 조회 페이지에서 사용(적금설명O) 	
   * /deposits/list
   * GET
   */
  const selectDepositDetailList = () => {
    axios.get(`${API_URL}${contextPath}${DepositAPI}/list`,
      {
        headers: {
          Authorization: `Bearer ${accessToken}`
        }
      })
      .then((response) => {
        console.log("적금 조회 페이지에서 사용(적금설명O) 성공", response.data.data);
        setSaveItems(response.data.data);
      })
      .catch((error) => {
        console.error('적금 조회 페이지에서 사용(적금설명O) 실패', error);
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
          {Array(parseInt(((amount/5000).toFixed(1)), 10) + 1).fill(null).map((count) => (
            <SavepageProposeItem 
              count={count}
              amount={amount && 0} 
            />
          ))}
        </div>
      </div>

      <BottomBarSpace />
    </div>
  )
};

export default SavePage;