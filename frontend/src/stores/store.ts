// 주석 부분은 토큰 관련 SecretKey 나오고 사용 (inbound / outbound / decrypt / encrypt)

import { combineReducers, configureStore } from "@reduxjs/toolkit";
import storageSession from "redux-persist/lib/storage/session";
import userSlice from "./userSlice";
import persistStore from "redux-persist/es/persistStore";
import persistReducer from "redux-persist/es/persistReducer";
import thunk from "redux-thunk";
import { createTransform } from "redux-persist";
import { appReducer } from "./appReducer";
// import { decryptData, encryptData } from "./encrypt";

const encryptTransform: any = createTransform();
// (inboundState, key) => encryptData(JSON.stringify(inboundState)),
// (outboundState, key) => JSON.parse(decryptData(outboundState)),

const reducers = combineReducers({
  user: userSlice.reducer,
  app: appReducer,
});

const persistConfig = {
  key: "root",
  storage: storageSession,
  transforms: [encryptTransform],
};

const persistedReducer = persistReducer(persistConfig, reducers);

const store = configureStore({
  reducer: persistedReducer,
  middleware: [thunk],
});

export const persistor = persistStore(store);
export default store;

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
