// react
import React, {useState} from "react";

// scss
import "../styles/components/RegistButtonStyle.scss";
import "../styles/components/RegistModalStyle.scss";

// icon
import XmarkIcon from "../assets/icons/icon-xmark.svg"

// components
import InputText from "../components/InputText";
import RegisterButton from "../components/RegistButton";
import Category from "./Category";
import { ChallengeItem, SpentItem } from "../constants/interfaces";

export interface RegistModalProps {
    isModalOpen : boolean;
    setIsModalOpen : any;
}

export default function RegistModal({ isModalOpen, setIsModalOpen }: RegistModalProps) {
  // context-path = `/api`;
  // const baseURL = 'https://haruman.site';
  // const baseURL = 'https://i9a608.p.ssafy.io:8000';
  // const CategorieAPI = '/categories';
  const [spentItem, setSpentItem] = useState<SpentItem>({
    category : null,
    color : null,
    content : null,
    payAmount : null,
  });
  const [categories, setCategories] = useState<ChallengeItem[]>([]);

  // 카테고리
  /**
    selectCategoryList   
    카테고리 전체 조회 
    (default + member’s custom)   /categories   
    GET
  */
  const selectCategoryList = () => {
    // const accessToken = sessionStorage.getItem("accessToken")
    // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);
    // 
    // axios.get(`${baseURL}${ChallengeAPI}/list?size=${itemsPerPage}&page=${currentPage}&sort=noticeIdx,desc`)
    //   .then((response) => {
    //     console.log("카테고리 전체 조회 성공");
    //     setCategories(response.data);
    //   })
    //   .catch((error) => {
    //     console.error('카테고리 전체 조회 실패', error);
    //   });
  };

  //모달 컨트롤
  const handleModal = () => {    
    setIsModalOpen(!isModalOpen);
  }

  return (
    <div id="regist_modal" className="regist_modal">
      <div>
        <div className="close_regist_modal">
          <button className="close_regist_modal_button" onClick={handleModal}>
            <img className="close_regist_modal_icon" src={XmarkIcon} alt="닫기"/>
          </button>
        </div>
        <div className="inputprice_space">
          <InputText
            className="InputText input_price disable_pliceholder"
            type="number"
            // alt="Input Email"
            placeholder="금액"
            value={spentItem.payAmount}
            onChange={(e) => setSpentItem({...spentItem, payAmount : e.target.value})} 
          />
          <p className="inputprice_space_text">원</p>
        </div>
        <div className="categoryselect_space">
        {
          categories.length !== 0 ?
          categories.map((category) => (
            <Category
              category = {category.category}
            //   color = {categoriy.color}
              onClick = {(e) => setSpentItem({...spentItem, category : e.target.category})}
            />
          ))
          :
          <div>
            생성된 카테고리가 없습니다!
          </div>
        }
        </div>
        <div className="inputprice_space">
          <InputText
            className="InputText input_memo disable_pliceholder"
            type="string"
            // alt="Input Email"
            placeholder="메모"
            value=""
            // 추후 email이 들어온다면 sending
            onChange={(e) => setSpentItem({...spentItem, content : e.target.value})} 
            // onKeyPress={handleKeyPress}
          />
        </div>
      </div>
    <div>
      {/* <RegisterButton
        text="OCR"
        className="setspentitem brand"
        onClick={handleModal}
      /> */}
      <RegisterButton
        className="setspentitem"
        text="확인"
        onClick={handleModal}
        // onKeyPress={handleKeyPress}
      />
    </div>
  </div>
  );
}