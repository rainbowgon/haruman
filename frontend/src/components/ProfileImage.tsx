/////////회원 관련 api명세서가 나오면 다시 진행합니다.///////

import React, { useRef } from "react";
// import { useDispatch, useSelector } from "react-redux";
// import axios from "axios";

const ProfileImageUploader: React.FC = () => {
  //   const dispatch = useDispatch();
  //   /////////////////////////header에 토큰이 아직 안나왔어요///////////////////////////////////////
  //   const profileId = useSelector((state: AppState) => state.profile.id);  // 가정: state에서 profileId를 가져옴
  //   const token = useSelector((state: AppState) => state.auth.token);  // 가정: state에서 token을 가져옴
  //   const inputFileRef = useRef<HTMLInputElement | null>(null);

  //   const handleImageChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
  //     const file = e.target.files?.[0];
  //     if (!file) return;

  //     const formData = new FormData();
  //     formData.append("profileImage", file);

  //     try {
  //       const response = await axios.post(`/profiles/upload/${profileId}`, formData, {
  //         headers: {
  //           "Content-Type": "multipart/form-data",
  //           "Authorization": `Bearer ${token}`,  // token을 헤더에 추가
  //         },
  //       });

  //       // 응답에서 이미지 URL을 추출하고, Redux store에 저장
  //       dispatch({
  //         type: "SET_PROFILE_IMAGE_URL",
  //         payload: response.data.data.profileImage,
  //       });

  //       alert(response.data.message);  // 응답 메시지 표시
  //     } catch (error) {
  //       console.error("Error uploading image:", error);
  //     }
  //   };

  return (
    <div>
      {/* //       <input
//         type="file"
//         ref={inputFileRef}
//         onChange={handleImageChange}
//         accept="image/*"
//         hidden
//       />
//       <button onClick={() => inputFileRef.current?.click()}>Upload Image</button> */}
    </div>
  );
};

export default ProfileImageUploader;
