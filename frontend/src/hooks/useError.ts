import { HttpStatusCode } from "axios";
import { updateAccessToken } from "../stores/userSlice";
import { useNavigate } from "react-router-dom";
import { useAppDispatch } from "./reduxHook";
import useLogOut from "./useLogout";

const useErrorHandlers = () => {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const logout = useLogOut();

  const errorHandlers = (response: any, callback: any, param?: any) => {
    if (response === undefined) {
      navigate("/error");
      return;
    }
    const statusCode = response.status;
    switch (statusCode) {
      case HttpStatusCode.Unauthorized:
        handle401Error(callback, param);
        break;
      case HttpStatusCode.NotFound:
      case HttpStatusCode.MethodNotAllowed:
      case HttpStatusCode.InternalServerError:
      case HttpStatusCode.ServiceUnavailable:
      case HttpStatusCode.GatewayTimeout:
      default:
        redirectErrorPage();
    }
  };

  function handle401Error(callback: any, param?: any) {}

  function redirectErrorPage() {
    navigate("/error");
  }

  return errorHandlers;
};

export default useErrorHandlers;
