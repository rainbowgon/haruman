// react
import React, { useEffect, useState } from "react";

// scss
import "../styles/components/RegistButtonStyle.scss";
import "../styles/components/RegistModalStyle.scss";

// icon
import XmarkIcon from "../assets/icons/icon-xmark.svg";

// urls
import { API_URL } from "../constants/urls";

// components
import InputText from "../components/InputText";
import RegisterButton from "../components/RegistButton";
import Category from "./Category";
import {
  ChallengeState,
  CategoryItem,
  SpentItem,
} from "../constants/interfaces";
import axios from "axios";
import Swal from "sweetalert2";

export interface RegistModalProps {
  isModalOpen: boolean;
  modalTop: number;
  setIsModalOpen: any;
  challengeInfo: ChallengeState;
  setChallengeInfo: any;
  handleWave: any;
}

export default function RegistModal({
  isModalOpen,
  modalTop,
  setIsModalOpen,
  challengeInfo,
  setChallengeInfo,
  handleWave,
}: RegistModalProps) {
  // 배포용
  // const accessToken = localStorage.getItem("accessToken");
  const accessToken =
    "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJfaWQiOiIzNmMwZmNkMC0wMTI1LTQzMmYtYWMwOS1kOTYwNDVjZjdhYjMiLCJpYXQiOjE2OTY1MjkwMzUsImV4cCI6MTY5NjUzMjYzNX0.hwCbjxYBY2o0wgFKSQOBvDzmiAuR_XZGY1THV9eVP8I";

  const contextPath = `/api`;

  const challengeAPI = "/challenges";
  const CategorieAPI = "/categories";

  const [spentItem, setSpentItem] = useState<SpentItem>({
    categoryId: 1,
    payTime: null,
    payAmount: null,
    content: null,
  });

  const [categories, setCategories] = useState<CategoryItem[]>([
    {
      categoryId: 2,
      name: "식사",
      color: "YELLOW_01",
      isDefault: "DEFAULT",
      cnt: null,
    },
    {
      categoryId: 3,
      name: "카페",
      color: "BROWN_01",
      isDefault: "DEFAULT",
      cnt: null,
    },
    {
      categoryId: 4,
      name: "군것질",
      color: "ORANGE_01",
      isDefault: "DEFAULT",
      cnt: null,
    },
    {
      categoryId: 5,
      name: "패션/뷰티",
      color: "RED_01",
      isDefault: "DEFAULT",
      cnt: null,
    },
    {
      categoryId: 6,
      name: "생활",
      color: "GREEN_01",
      isDefault: "DEFAULT",
      cnt: null,
    },
    {
      categoryId: 7,
      name: "건강",
      color: "BLUE_01",
      isDefault: "DEFAULT",
      cnt: null,
    },
    {
      categoryId: 8,
      name: "유흥",
      color: "PINK_01",
      isDefault: "DEFAULT",
      cnt: null,
    },
    {
      categoryId: 9,
      name: "교통",
      color: "BLACK_02",
      isDefault: "DEFAULT",
      cnt: null,
    },
    {
      categoryId: 10,
      name: "여가",
      color: "PURPLE_01",
      isDefault: "DEFAULT",
      cnt: null,
    },
    {
      categoryId: 1,
      name: "기타",
      color: "BLACK_01",
      isDefault: "DEFAULT",
      cnt: null,
    },
  ]);

  useEffect(() => {
    selectCategoryList();
  }, []);

  /**
    createExpense
    지출 내역 직접 입력
    /challenges/{challenge-id}
    POST
  */
  const createExpense = () => {
    if (
      spentItem.payAmount === null ||
      isNaN(spentItem.payAmount) ||
      spentItem.payAmount === 0
    ) {
      showAlert("금액을 입력해주세요!");
      return;
    }
    if (challengeInfo.challengeId === undefined) {
      showAlert("챌린지가 정상적인지 확인해 주세요!");
      return;
    }
    if (spentItem.categoryId === null || isNaN(spentItem.categoryId)) {
      showAlert("카테고리가 없어요!");
      return;
    }

    axios
      .post(
        `${API_URL}${contextPath}${challengeAPI}/${challengeInfo.challengeId}`,
        spentItem,
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        },
      )
      .then((response) => {
        // showAlert("지출 내역이 등록되었어요!");
        handleWave(
          challengeInfo.targetAmount,
          challengeInfo.leftoverAmount -
            (spentItem.payAmount ? spentItem.payAmount : 0),
        );
        setChallengeInfo({
          ...challengeInfo,
          leftoverAmount:
            challengeInfo.leftoverAmount -
            (spentItem.payAmount !== null ? spentItem.payAmount : 0),
          usedAmount:
            challengeInfo.usedAmount +
            (spentItem.payAmount !== null ? spentItem.payAmount : 0),
        });
        resetSpentItem();
        handleModal();
      })
      .catch((error) => {
        console.error("서버로부터 지출 내역 직접 입력 실패", error);
        showAlert("지출 내역이 등록이 실패했어요");
      });
  };

  // 카테고리
  /**
    selectCategoryList   
    카테고리 전체 조회 
    (default + member’s custom)   /categories   
    GET
  */
  const selectCategoryList = () => {
    axios
      .get(`${API_URL}${contextPath}${CategorieAPI}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        setCategories(response.data.data);
      })
      .catch((error) => {
        setCategories([]);
      });
  };

  //모달 컨트롤
  const handleModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  // 제출 시간 삽입
  const RegisterValue = () => {
    console.log(
      "spentItem.payTime === null || spentItem.payTime === ",
      spentItem.payTime === null || spentItem.payTime === "",
    );
    if (spentItem.payTime === null || spentItem.payTime === "") {
      const currentDate = new Date();
      const year = currentDate.getFullYear();
      const month = String(currentDate.getMonth() + 1).padStart(2, "0");
      const day = String(currentDate.getDate()).padStart(2, "0");
      const hours = String(currentDate.getHours()).padStart(2, "0");
      const minutes = String(currentDate.getMinutes()).padStart(2, "0");
      const seconds = String(currentDate.getSeconds()).padStart(2, "0");
      const milliseconds = String(currentDate.getMilliseconds()).padStart(
        6,
        "0",
      );

      const formattedDate = `${year}-${month}-${day}T${hours}:${minutes}:${seconds}.${milliseconds}`;

      console.log(formattedDate);
      setSpentItem({ ...spentItem, payTime: formattedDate });
    }
    createExpense();
  };

  const resetSpentItem = () => {
    setSpentItem({
      categoryId: 1,
      payTime: "",
      payAmount: 0,
      content: "",
    });
  };

  const showAlert = (text: string) => {
    Swal.fire({
      text,
    });
  };

  return (
    <div
      id="regist_modal"
      className="regist_modal"
      style={{ top: `${modalTop}%` }}
    >
      <div>
        <div className="close_regist_modal">
          <button
            className="close_regist_modal_button"
            onClick={handleModal}
          >
            <img
              className="close_regist_modal_icon"
              src={XmarkIcon}
              alt="닫기"
            />
          </button>
        </div>
        <div className="inputprice_space">
          <InputText
            className="InputText input_price disable_pliceholder"
            type="number"
            placeholder="금액"
            value={spentItem.payAmount}
            onChange={(e) =>
              setSpentItem({
                ...spentItem,
                payAmount: parseInt(e.target.value, 10),
              })
            }
          />
          <p className="inputprice_space_text">원</p>
        </div>
        <div className="categoryselect_space">
          {categories ? (
            categories.map((category) => (
              <Category
                name={category.name}
                color={category.color}
                onClick={(e) =>
                  setSpentItem({
                    ...spentItem,
                    categoryId: category.categoryId,
                  })
                }
                className={
                  spentItem.categoryId === category.categoryId
                    ? "highlighted"
                    : ""
                }
              />
            ))
          ) : (
            <div>생성된 카테고리가 없습니다!</div>
          )}
        </div>
        <div className="inputprice_space">
          <InputText
            className="InputText input_memo disable_pliceholder"
            type="string"
            placeholder="메모"
            value={spentItem.content}
            onChange={(e) =>
              setSpentItem({ ...spentItem, content: e.target.value })
            }
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
          onClick={RegisterValue}
          // onKeyPress={handleKeyPress}
        />
      </div>
    </div>
  );
}
