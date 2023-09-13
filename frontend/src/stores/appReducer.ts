import { SET_ACTIVE_PAGE } from "./actions";

const initialState = {
  activePage: 0,
};

export const appReducer = (state = initialState, action: any) => {
  switch (action.type) {
    case SET_ACTIVE_PAGE:
      return { ...state, activePage: action.payload };
    default:
      return state;
  }
};
