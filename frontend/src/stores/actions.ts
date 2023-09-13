export const SET_ACTIVE_PAGE = "SET_ACTIVE_PAGE";

export const setActivePage = (page: number) => ({
  type: SET_ACTIVE_PAGE,
  payload: page,
});
