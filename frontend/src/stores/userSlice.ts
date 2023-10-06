// import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import React from "react";
// interface LoginResponse {
//   accessToken: string;
//   refreshToken: string;
//   userInfo: UserInfoType;
// }

// interface UserState extends LoginResponse {
//   isLogin: boolean;
// }

// const initialState: UserState = {
//   accessToken: "",
//   refreshToken: "",
//   userInfo: {
//     id: "",
//     userEmail: "",
//     userName: "",
//     role: "",
//   },
//   isLogin: false,
// };

// const userSlice = createSlice({
//   name: "userSlice",
//   initialState,
//   reducers: {
//     userSliceLogin: (state, action: PayloadAction<LoginResponse>) => {
//       state.accessToken = action.payload.accessToken;
//       state.refreshToken = action.payload.refreshToken;
//       state.userInfo.userEmail = action.payload.userInfo.userEmail;
//       state.userInfo.userName = action.payload.userInfo.userName;
//       state.userInfo.role = action.payload.userInfo.role;
//       state.isLogin = true;
//     },
//     userSliceLogout: (state) => {
//       return initialState;
//     },
//     updateAccessToken: (
//       state,
//       action: PayloadAction<{ accessToken: string }>,
//     ) => {
//       state.accessToken = action.payload.accessToken;
//     },
//     updateUserName: (state, action: PayloadAction<string>) => {
//       state.userInfo.userName = action.payload;
//     },
//   },
// });

// export default userSlice;
// export const {
//   userSliceLogin,
//   userSliceLogout,
//   updateAccessToken,
//   updateUserName,
// } = userSlice.actions;

// export interface UserInfoType {
//   userEmail: string;
//   userName: string;
//   role: string;
//   id: string;
// }
