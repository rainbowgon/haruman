import React, { useState, useEffect, useRef } from "react";
import { Link } from "react-router-dom";
import Swal from "sweetalert2";

// style
import MainStyle from "../components/MainStyle";
import "../styles/theme.css";
import "../styles/wave.scss";

// components
import RegisterButton from "../components/RegistButton";
import RegistModal from "../components/RegistModal";

const Homepage = () => {
  // const baseURL = 'https://i9a608.p.ssafy.io:8000';
  // const ChallengeAPI = '/challenges';
  const canStart:number[] = [5, 12];

  const [currentDate, setCurrentDate] = useState(new Date());
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalTop, setModalTop] = useState(100);
  const [challenge, setChallenge] = useState(true);
  const [height, setHeight] = useState(100);
  const [challengeInfo, setChallengeInfo] = useState();
  const sidebarRef = useRef();

  // 매 초마다 시간 재설정
  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentDate(new Date());
    }, 1000);

    return () => clearInterval(intervalId);
  }, []);

  useEffect(() => {
    // const regist_modal = document.querySelector("#regist_modal");
  
    // let curModalTop:number = modalTop;
    // let targetModalTop: number = isModalOpen ? 97: 0;
  
    // function modalframe() {
    //   curModalTop = lerp(curModalTop, targetModalTop, 0.1);
      
    //   console.log("targetModalTop - curModalTop" , isModalOpen, targetModalTop, curModalTop);
    //   setModalTop(curModalTop);

    //   if(targetModalTop - curModalTop > 0.1 || targetModalTop - curModalTop < -0.1){
    //     requestAnimationFrame(modalframe);
    //   }
    // }
    // requestAnimationFrame(modalframe);

    // function lerp(s:number, e:number, a:number) {
    //   return s + (0-s) * a;
    // }
  }, [isModalOpen]);

  const handleStartChallenge = () => {
    // 테스트 끝나면 open

    // if(currentDate.getHours() < canStart[0]) {
    //   showAlert(`${canStart[0]}시에 시작할 수 있습니다.`);
    //   return;
    // }
    // if(currentDate.getHours() >= canStart[1]){
    //   showAlert(`시작 가능한 시간이 지났습니다. 내일 도전해 보세요!`);
    //   return;
    // }

    const nowChallenge = !challenge;
  
    //챌린지 시작 
    setChallenge(nowChallenge);

    startChallenge();

    let curHeight:number = height;
    let targetHeight: number = nowChallenge ? height + 5 : height - 100;  // min : 0(vw), max : 60(vw)
  
    function frame() {
      curHeight = lerp(curHeight, targetHeight, 0.02);
      
      setHeight(curHeight);

      if(targetHeight - curHeight > 0.1 || targetHeight - curHeight < -0.1){
        requestAnimationFrame(frame);
      }
    }
    requestAnimationFrame(frame);

    function lerp(s:number, e:number, a:number) {
      return s + (0-s) * a;
    }
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
    //     setChallengeInfo(response.data);
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
      // const challenge_id = sdlkfsdj;
      // axios.post(`${baseURL}${ChallengeAPI}/receipt/${challenge_id}`, null,
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
    //     setChallengeInfo(response.data);
    //   })
    //   .catch((error) => {
    //     console.error('해당일 챌린지 조회 (일일 잔액, 지출 내역 리스트) 조회 실패', error);
    //   });
  };

  
  const handleModal = () => {    
    //모달 컨트롤
    setIsModalOpen(!isModalOpen);
  }
  

  const showAlert = (text:string) => {
    Swal.fire({
      text,
    });
  };


  return (
    <MainStyle>
      <div className="homepage">
        <div className="homepage_header">
          <p className="homepage_text">{currentDate.getMonth()+1}월 {currentDate.getDate()}일</p>
          {
            challenge
            ?<>
              <div className="homepage_header_title">
                <h2 className="homepage_header_title_text">오늘의 챌린지
                </h2>
              </div>
              <p className="homepage_text">{}명의 유저가 먼저 진행하고 있어요!</p>
            </>
            :<>
              <div className="homepage_header_title">
                <h2 className="homepage_header_title_text">
                  {
                    currentDate.getHours() > 14 &&
                    "0"
                  }
                  {
                    23 - currentDate.getHours()
                  } : {
                    currentDate.getMinutes() >= 50 &&
                    "0"
                  }
                  {
                  59 - currentDate.getMinutes()
                  } : {
                    currentDate.getSeconds() >= 50 &&
                    "0"
                  }
                  {
                  59 - currentDate.getSeconds()
                  }
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
                      <h2>10,000</h2>
                      <h3>원</h3>
                    </div>
                  </div>
                  <p className="challenge_link_text">지출내역으로 이동</p>
                </Link>
              }
            </div>
            {
              <div id="wave_height" style={{ height: `${height}vw`}}/>
            }
            <div>
              <svg className="waves" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
                <defs>
                  <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
                </defs>
                <g className="parallax">
                  <use xlinkHref="#gentle-wave" x="48" y="0" fill="var(--brand2_op_60)" />
                  <use xlinkHref="#gentle-wave" x="48" y="3" fill="var(--brand2_op_40)" />
                  <use xlinkHref="#gentle-wave" x="48" y="5" fill="var(--brand2_op_20)" />
                  <use xlinkHref="#gentle-wave" x="48" y="5" fill="var(--gradation-start)" />
                </g>
              </svg>
            </div>
            <div className="main_circle_gradation"/>
          </div>
        </div>
        <div>
          {/* <div className="getspace"/> */}
          {
            <div id="wave_height" style={{ height: `${height}vw`}}/>
          }
          <svg className="waves" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
            <defs>
              <path id="gentle-wave" d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
            </defs>
            <g className="parallax">
              <use xlinkHref="#gentle-wave" x="48" y="0" fill="var(--brand2_op_60)" />
              <use xlinkHref="#gentle-wave" x="48" y="3" fill="var(--brand2_op_40)" />
              <use xlinkHref="#gentle-wave" x="48" y="5" fill="var(--brand2_op_20)" />
              <use xlinkHref="#gentle-wave" x="48" y="8" fill="var(--gradation-start)" />
            </g>
          </svg>
          <div className="bottom_gradation"/>
        </div>
        <div className="input_purchase">
          {
            challenge
            ?
            <div className="information_block">
              <div className="information_block_button">
                <p className="information_block_title">챌린지 시작 가능 시간</p>
                <p className="information_block_content">05:00 ~ 12:00</p>
              </div>
            </div>
            :
            <RegisterButton
              text="지출 입력"
              className="white"
              onClick={handleModal}
            />
          }
        </div>
        {
          isModalOpen &&
          // <div id="regist_modal" className="regist_modal" style={{ top: `${modalTop}vh`}}>
          <RegistModal
            isModalOpen = {isModalOpen}
            setIsModalOpen = {setIsModalOpen}
          />
        }
      </div>
    </MainStyle>
  );
};

export default Homepage;