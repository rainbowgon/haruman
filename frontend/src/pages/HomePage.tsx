import React, { useState } from "react";
import MainStyle from "../components/MainStyle";
import "../styles/theme.css";
import "../styles/wave.scss";
import { Link } from "react-router-dom";

// components
import InputText from "../components/InputText";
import RegisterButton from "../components/RegistButton";

const Homepage = () => {
  const baseURL = 'https://i9a608.p.ssafy.io:8000';
  const ChallengeAPI = '/challenges';
  const CategorieAPI = '/categories';
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [challenge, setChallenge] = useState(true);
  const [height, setHeight] = useState(100);

  const handleStartChallenge = () => {
    const nowChallenge = !challenge;
  
    //챌린지 시작 
    setChallenge(nowChallenge);

    startChallenge();
  
    let targetHeight: number = nowChallenge ? height + 5 : height - 100;
  
    setTimeout(() => {
      console.log(targetHeight);
      setHeight(targetHeight);
    }, 0);
  };

  // 챌린지 관련
  /**
    startChallenge	
    챌린지 시작	
    /challenges	
    POST
  */

  const startChallenge = () => {
    // const accessToken = sessionStorage.getItem("accessToken")
    // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);

    // let guest_id = null;
    // if (from === "FriendList"){
    //   guest_id = parseInt(friend.userId, 10);
    // } else {
    //   guest_id = parseInt(friend.idx, 10);
    // }

    // axios.post(`${baseURL}${ChallengeAPI}`, null,
    // {
    //   headers: {
    //     Authorization: `Bearer ${accessToken}`
    //   }
    // })
    // .then((response) => {
    //     console.log("첼린지 시작");
    //     showAlert("success", "첼린지 시작");
    //     setCurStatus(2);
    // })
    // .catch((error) => {
    //     console.error("서버로부터 챌린지 시작 실패", error);
    //     showAlert("error", "챌린지 시작 실패입니다.");
    //     console.error(error.code);
    // });
  }

  /**
    createReceipt	
    지출 내역 영수증 입력	
    /challenges/receipt/{challenge-id}	
    POST
  */
    const createReceipt = () => {
      // const accessToken = sessionStorage.getItem("accessToken")
      // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);
  
      // let guest_id = null;
      // if (from === "FriendList"){
      //   guest_id = parseInt(friend.userId, 10);
      // } else {
      //   guest_id = parseInt(friend.idx, 10);
      // }
  
      // axios.post(`${baseURL}${ChallengeAPI}/receipt/{challenge-id}`, null,
      // {
      //   headers: {
      //     Authorization: `Bearer ${accessToken}`
      //   }
      // })
      // .then((response) => {
      //     console.log("지출 내역 영수증 입력");
      //     showAlert("success", "지출 내역 영수증 입력");
      //     setCurStatus(2);
      // })
      // .catch((error) => {
      //     console.error("서버로부터 지출 내역 영수증 입력 실패", error);
      //     showAlert("error", "지출 내역 영수증 입력 실패입니다.");
      //     console.error(error.code);
      // });
    }
  

  /**
    createExpense
    지출 내역 직접 입력
    /challenges/{challenge-id}
    POST
  */
    const createExpense = () => {
      // const accessToken = sessionStorage.getItem("accessToken")
      // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);
  
      // let guest_id = null;
      // if (from === "FriendList"){
      //   guest_id = parseInt(friend.userId, 10);
      // } else {
      //   guest_id = parseInt(friend.idx, 10);
      // }
  
      // axios.post(`${baseURL}${ChallengeAPI}/{challenge-id}`, null,
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


  /**
    updateExpense	
    지출 내역 수정	
    /challenges	
    PATCH
  */
    const updateExpense = () => {
      // const accessToken = sessionStorage.getItem("accessToken")
      // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);
  
      // let guest_id = null;
      // if (from === "FriendList"){
      //   guest_id = parseInt(friend.userId, 10);
      // } else {
      //   guest_id = parseInt(friend.idx, 10);
      // }
  
      // axios.patch(`${baseURL}${ChallengeAPI}`, "edit",
      // {
      //   headers: {
      //     Authorization: `Bearer ${accessToken}`
      //   }
      // })
      // .then((response) => {
      //     console.log("지출 내역 수정");
      //     showAlert("success", "지출 내역 수정");
      //     setCurStatus(2);
      // })
      // .catch((error) => {
      //     console.error("서버로부터 지출 내역 수정 실패", error);
      //     showAlert("error", "지출 내역 수정 실패입니다.");
      //     console.error(error.code);
      // });
    }



  /**
    deleteExpense	
    지출 내역 삭제	
    /challenges/{expense-id}	
    DELETE
  */
  const deleteExpense = () => {
    // const accessToken = sessionStorage.getItem("accessToken")
    // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);

    // let guest_id = null;
    // if (from === "FriendList"){
    //     guest_id = parseInt(friend.userId, 10);
    // } else {
    //     guest_id = parseInt(friend.idx, 10);
    // }

    // axios.delete(`${baseURL}${ChallengeAPI}/{expense-id}`,
    // {
    //   params: {
    //     guest_id:guest_id,
    //     host_id:host_id
    //   },
    //   headers: {
    //     Authorization: `Bearer ${accessToken}`
    //   }
    // })
    // .then((response) => {
    //   showAlert("success", "지출 내역 삭제 요청 취소 성공입니다.");
    //   setCurStatus(0);
    // })
    // .catch((error) => {
    //   console.error("서버로부터 지출 내역 삭제 요청 실패", error);
    //   showAlert("error", "지출 내역 삭제 요청 실패입니다.");
    //   console.error(error.code);
    // });
  }


  /**
    selectDailyChallenge	
    해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트)	
    /challenges?date={date}	
    GET
  */
  const selectDailyChallenge = () => {
    // const accessToken = sessionStorage.getItem("accessToken")
    // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);
    // 
    // axios.get(`${baseURL}${ChallengeAPI}?date={date}`)
    //   .then((response) => {
    //     console.log("해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트)");
    //     setPostList(response.data);
    //   })
    //   .catch((error) => {
    //     console.error('해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트) 조회 실패', error);
    //   });
  };


  // 카테고리
  /**
    selectCategoryList	
    카테고리 전체 조회 
    (default + member’s custom)	/categories	
    GET
  */
  const selectCategoryList = () => {
    // const accessToken = sessionStorage.getItem("accessToken")
    // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);
    // 
    // axios.get(`${baseURL}${ChallengeAPI}/list?size=${itemsPerPage}&page=${currentPage}&sort=noticeIdx,desc`)
    //   .then((response) => {
    //     console.log("카테고리 전체 조회 성공");
    //     setPostList(response.data);
    //   })
    //   .catch((error) => {
    //     console.error('카테고리 전체 조회 실패', error);
    //   });
  };
  

  const handleModal = () => {
    setIsModalOpen(!isModalOpen);
  }
  
  return (
    <MainStyle>
      <div className="homepage">
        <div className="homepage_header">
          {/* <p className="homepage_text">{}월 {}일</p> */}
          <p className="homepage_text">12월 31일</p>
          {
            challenge
            ?<>
              <div className="homepage_header_title">
                <h2 className="homepage_header_title_text">오늘의 챌린지
                  <div className="style_horizon_line"/>
                </h2>
              </div>
              <p className="homepage_text">{}명의 유저가 먼저 진행하고 있어요!</p>
            </>
            :<>
              <div className="homepage_header_title">
                <h2 className="homepage_header_title_text">13{} : 24{}
                  <div className="style_horizon_line"/>
                </h2>
                
              </div>
              <p className="homepage_text">{}명의 유저와 함께하고 있어요!</p>
            </>
          }
        </div>
        <div>
          <div className="main_circle">
            <div className="start_challenge">
              {
                challenge 
                ?
                <div className="start_challenge_button" onClick={handleStartChallenge}>
                  <h2 className="start_challenge_button_title">챌린지</h2>
                  <h2 className="start_challenge_button_title">시작</h2>
                </div>
                :
                <Link to="/today" className="linkto_today">
                  <div className="progress_challenge">
                    <div className="progress_challenge_title">
                      <h2>8,{height}</h2>
                      <h3>원</h3>
                    </div>
                  </div>
                  <p className="challenge_link_text">지출내역으로 이동</p>
                </Link>
              }
            </div>
            {
              <div style={{ height: `${height}vw`}}/>
            }
            <div>
              <svg className="waves" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
                <defs>
                  <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
                </defs>
                <g className="parallax">
                  <use xlinkHref="#gentle-wave" x="48" y="0" fill="rgba(93,215,190,0.7)" />
                  <use xlinkHref="#gentle-wave" x="48" y="5" fill="var(--brand2_op_40)" />
                  <use xlinkHref="#gentle-wave" x="48" y="3" fill="rgba(93,215,190,0.3)" />
                  <use xlinkHref="#gentle-wave" x="48" y="5" fill="var(--gradation-start)" />
                </g>
              </svg>
            </div>
            <div className="main_circle_gradation"/>
          </div>
        </div>
          <div className="getspace"/>
          <div>
            <svg className="waves" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
              <defs>
                <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
              </defs>
              <g className="parallax">
                <use xlinkHref="#gentle-wave" x="48" y="0" fill="rgba(93,215,190,0.7" />
                <use xlinkHref="#gentle-wave" x="48" y="3" fill="var(--brand2_op_40)" />
                <use xlinkHref="#gentle-wave" x="48" y="5" fill="rgba(93,215,190,0.3)" />
                <use xlinkHref="#gentle-wave" x="48" y="8" fill="var(--gradation-start)" />
              </g>
            </svg>
          <div className="bottom_gradation"/>
        </div>
        <div className="input_purchase">
          <RegisterButton
            text="지출 입력"
            className="white"
            onClick={handleModal}
          />
        </div>
        {
          isModalOpen &&
          <div className="regist_modal">
            <div>
              <div>
                <button onClick={handleModal}>✖</button>
              </div>
              <div>
                <h2 className="regist_title">사용 내역 입력</h2>
              </div>
              <div>
                price 원
              </div>
              <div>
                카테고리
              </div>
              <div>
                <InputText
                  className="InputText"
                  type="email"
                  // alt="Input Email"
                  placeholder="내용"
                  value=""
                  // 추후 email이 들어온다면 sending
                  onChange={(e) => console.log(e.target.value)}
                  // onKeyPress={handleKeyPress}
                />
              </div>
              <div>
                메모
              </div>
            </div>
            <div>
              <RegisterButton
                text="OCR"
                className="white"
                onClick={handleModal}
              />
              <RegisterButton
                text="확인"
                onClick={handleModal}
              />
              {/* <RegisterButton
                text="OCR"
                className="mini_type white"
                onClick={handleModal}
              />
              <RegisterButton
                text="➕"
                className="mini_type white"
                onClick={handleModal}
              /> */}
            </div>
          </div>
        }
      </div>
    </MainStyle>
  );
};

export default Homepage;