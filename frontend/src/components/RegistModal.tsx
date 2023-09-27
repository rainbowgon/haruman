// react
import React, {useEffect, useState} from "react";

// scss
import "../styles/components/RegistButtonStyle.scss";
import "../styles/components/RegistModalStyle.scss";

// icon
import XmarkIcon from "../assets/icons/icon-xmark.svg"

// urls
import { API_URL, SERVER_URL } from "../constants/urls";

// components
import InputText from "../components/InputText";
import RegisterButton from "../components/RegistButton";
import Category from "./Category";
import { CategoryItem, SpentItem } from "../constants/interfaces";
import axios from "axios";

export interface RegistModalProps {
    isModalOpen : boolean;
    modalTop: number;
    setIsModalOpen : any;
}

export default function RegistModal(
  { 
    isModalOpen, 
    modalTop,
    setIsModalOpen 
  }: RegistModalProps) {

  // const baseURL = 'https://haruman.site';
  const contextPath = `/api`;
  const CategorieAPI = '/categories';

  const [spentItem, setSpentItem] = useState<SpentItem>({
    category : null,
    color : null,
    content : null,
    payAmount : null,
  });
  const [categories, setCategories] = useState<CategoryItem[]>([
    {
      categoryId :2,
      name :"식사",
      color :"YELLOW_01",
      isDefault :"DEFAULT"
    },
    {
      categoryId :3,
      name :"카페",
      color :"BROWN_01",
      isDefault :"DEFAULT"
    },
    {
      categoryId :4,
      name :"군것질",
      color :"ORANGE_01",
      isDefault :"DEFAULT"
    },
    {
      categoryId :5,
      name :"패션/뷰티",
      color :"RED_01",
      isDefault :"DEFAULT"
    },
    {
      categoryId :6,
      name :"생활",
      color :"GREEN_01",
      isDefault :"DEFAULT"
    },
    {
      categoryId :7,
      name :"건강",
      color :"BLUE_01",
      isDefault :"DEFAULT"
    },
    {
      categoryId :8,
      name :"유흥",
      color :"PINK_01",
      isDefault :"DEFAULT"
    },
    {
      categoryId :9,
      name :"교통",
      color :"BLACK_02",
      isDefault :"DEFAULT"
    },
    {
      categoryId :10,
      name :"여가",
      color :"PURPLE_01",
      isDefault :"DEFAULT"
    },
    {
      categoryId :1,
      name :"기타",
      color :"BLACK_01",
      isDefault :"DEFAULT"
    },
  ]);

  useEffect(() => {
    selectCategoryList();
  }, [])

  /**
    createExpense
    지출 내역 직접 입력
    /challenges/{challenge-id}
    POST
  */
  const createExpense = () => {
    // const accessToken = sessionStorage.getItem("accessToken")

    // axios.post(`${baseURL}${contextPath}${ChallengeAPI}/{challenge-id}`, null,
    // {
    //   headers: {
    //     Authorization: `Bearer ${accessToken}`
    //   }
    // })
    // .then((response) => {
    //     console.log("지출 내역 직접 입력");
    //     showAlert("success", "지출 내역 직접 입력");
    //     setCurStatus(2);
    // })
    // .catch((error) => {
    //     console.error("서버로부터 지출 내역 직접 입력 실패", error);
    //     showAlert("error", "지출 내역 직접 입력 실패입니다.");
    //     console.error(error.code);
    // });
  }

  // 카테고리
  /**
    selectCategoryList   
    카테고리 전체 조회 
    (default + member’s custom)   /categories   
    GET
  */
  const selectCategoryList = () => {
    const accessToken = sessionStorage.getItem("accessToken")
    // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);
    
    console.log("카테고리 전체 조회");

    axios.get(`${API_URL}${contextPath}${CategorieAPI}`,
      {
        headers: {
          Authorization: `Bearer ${accessToken}`
        }
      })
      .then((response) => {
        console.log("카테고리 전체 조회 성공", response.data.data);
        setCategories(response.data.data);
      })
      .catch((error) => {
        console.error('카테고리 전체 조회 실패', error);
        setCategories([]);
      });
  };

  //모달 컨트롤
  const handleModal = () => {    
    setIsModalOpen(!isModalOpen);
  }

  return (
    <div id="regist_modal" className="regist_modal"  style={{ top: `${modalTop}%`}}>
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
          categories ?
          categories.map((category) => (
            <Category
              name = {category.name}
              color = {category.color}
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