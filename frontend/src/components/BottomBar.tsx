import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setActivePage } from "../stores/actions";
import { AppState } from "../stores/state";
import { useNavigate, useLocation } from "react-router-dom";

// icons
// import { ReactComponent as CalendarIcon } from "../assets/icons/icon-calendarday.svg";
// import { ReactComponent as ChartsetIcon } from "../assets/icons/icon-chartset.svg";
// import { ReactComponent as HomeIcon } from "../assets/icons/icon-home.svg";
// import { ReactComponent as PiggybankIcon } from "../assets/icons/icon-piggybank.svg";
// import { ReactComponent as BurgermenuIcon } from "../assets/icons/icon-burgermenu.svg";
import CalendarIcon from "../assets/icons/icon-calendarday.svg";
import ChartsetIcon from "../assets/icons/icon-chartset.svg";
import HomeIcon from "../assets/icons/icon-home.svg";
import PiggybankIcon from "../assets/icons/icon-piggybank.svg";
import BurgermenuIcon from "../assets/icons/icon-burgermenu.svg";


// css
import "../styles/theme.css";
import "../styles/BottomBar.scss";

const BottomBar: React.FC = () => {
  const dispatch = useDispatch();
  const activePage = useSelector((state: AppState) => state.activePage);
  const navigate = useNavigate();
  const location = useLocation();

  // const [pages] = useState([
  //   { name: "Calendar", icon: <CalendarIcon />, path: "/calendar" },
  //   { name: "Ranking", icon: <ChartsetIcon />, path: "/ranking" },
  //   { name: "Home", icon: <HomeIcon />, path: "/home" },
  //   { name: "Save", icon: <PiggybankIcon />, path: "/save" },
  //   { name: "MyPage", icon: <BurgermenuIcon />, path: "/mypage" },
  // ]);
  const [pages] = useState([
    { name: "Calendar", icon: CalendarIcon, path: "/calendar" },
    { name: "Ranking", icon: ChartsetIcon, path: "/ranking" },
    { name: "Home", icon: HomeIcon, path: "/home" },
    { name: "Save", icon: PiggybankIcon, path: "/save" },
    { name: "MyPage", icon: BurgermenuIcon, path: "/mypage" },
  ]);

  useEffect(() => {
    const currentPage = pages.findIndex((p) => p.path === location.pathname);
    if (currentPage !== -1) {
      dispatch(setActivePage(currentPage));
    }
  }, [location.pathname, pages, dispatch]);

  return (
    <div className="bottom-bar">
      {pages.map((menu, index) => (
        <button
          key={index}
          className={activePage === index ? "active" : "inactive"}
          onClick={() => {
            navigate(menu.path);
            dispatch(setActivePage(index));
          }}
        >
          <span
            className={location.pathname === menu.path ? "active-icon" : "inactive-icon"}
          >
            <img className="bottom-bar-image" src={menu.icon} alt="menu" />
          </span>
          {/* <span>{menu.name}</span> */}
        </button>
      ))}
    </div>
  );
};

export default BottomBar;
