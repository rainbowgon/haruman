import { SERVER_URL } from "../constants/urls";
import axios from "axios";

export const axiosInstance = axios.create({
  baseURL: `${SERVER_URL}`,
  withCredentials: true,
  headers: {
    "Content-type": "application/json",
  },
});
