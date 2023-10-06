import { API_URL, SERVER_URL } from "../constants/urls";
import axios from "axios";

export const axiosInstance = axios.create({
  baseURL: `${API_URL}`,
  withCredentials: true,
  headers: {
    "Content-type": "application/json",
  },
});
