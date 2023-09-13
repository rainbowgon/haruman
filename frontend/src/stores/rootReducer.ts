import { combineReducers } from "redux";
import userSlice from "./userSlice";
import { appReducer } from "./appReducer";

const rootReducer = combineReducers({
  user: userSlice.reducer,
  app: appReducer,
});

export default rootReducer;
