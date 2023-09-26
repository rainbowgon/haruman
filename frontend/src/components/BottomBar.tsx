import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setActivePage } from "../stores/actions";
import { AppState } from "../stores/state";
import { useNavigate, useLocation } from "react-router-dom";

// icons
import CalendarIcon from "../assets/icons/icon-calendarday.svg";
import ChartsetIcon from "../assets/icons/icon-chartset.svg";
import HomeIcon from "../assets/icons/icon-home.svg";
import PiggybankIcon from "../assets/icons/icon-piggybank.svg";
import BurgermenuIcon from "../assets/icons/icon-burgermenu.svg";

// css
import "../styles/theme.css";
import "../styles/components/BottomBarStyle.scss";

const BottomBar: React.FC = () => {
  const dispatch = useDispatch();
  const activePage = useSelector((state: AppState) => state.activePage);
  const navigate = useNavigate();
  const location = useLocation();
  // const [isNavUp, setIsNavUp] = useState(false);
  // const [lastScrollTop, setLastScrollTop] = useState(0);
  // const delta = 5;

  const [pages] = useState([
    { name: "Calendar", icon: CalendarIcon, path: "/calendar" },
    { name: "Ranking", icon: ChartsetIcon, path: "/ranking" },
    { name: "Home", icon: HomeIcon, path: "/home" },
    { name: "Save", icon: PiggybankIcon, path: "/save" },
    { name: "MyPage", icon: BurgermenuIcon, path: "/mypage" },
  ]);

  // useEffect(() => {
  //   const handleScroll = (): void => {
  //     const currentScrollPosition = window.scrollY;

  //     if (currentScrollPosition > lastScrollPosition) {
  //       setIsHidden(true);
  //     } else {
  //       // 위로 스크롤
  //       setIsHidden(false);
  //     }

  //     setLastScrollPosition(currentScrollPosition);
  //   };
  //   window.addEventListener("scroll", handleScroll);
  //   return () => window.removeEventListener("scroll", handleScroll);
  // }, [lastScrollPosition]);
  // console.log("lastScrollPosition", lastScrollPosition);

  // useEffect(() => {
  //   const handleScroll = () => {
  //     const st = window.scrollY;

  //     if (Math.abs(lastScrollTop - st) <= delta) return;

  //     if (st > lastScrollTop) {
  //       setIsNavUp(true); // 아래로 스크롤할 때
  //     } else if (
  //       st + window.innerHeight <
  //       document.documentElement.scrollHeight
  //     ) {
  //       setIsNavUp(false); // 위로 스크롤할 때
  //     }

  //     setLastScrollTop(st);
  //   };

  //   window.addEventListener("scroll", handleScroll);

  //   return () => {
  //     window.removeEventListener("scroll", handleScroll);
  //   };
  // }, [lastScrollTop]);

  // const bottomBarStyle = isNavUp
  //   ? { transform: "translateY(100%)" }
  //   : { transform: "translateY(0)" };
  useEffect(() => {
    const currentPage = pages.findIndex((p) => p.path === location.pathname);
    if (currentPage !== -1) {
      dispatch(setActivePage(currentPage));
    }
  }, [location.pathname, pages, dispatch]);
  // const bottomBarStyle = isHidden
  // ? { transform: "translateY(100%)" }
  // : { transform: "translateY(0)" };
  if (
    location.pathname === "/login" ||
    location.pathname === "/" ||
    location.pathname === "/signup" ||
    location.pathname === "/oauth/kakao/redirect"
  ) {
    return null;
  }
  return (
    <div
      className="bottom-bar"
      // style={bottomBarStyle}
    >
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
            className={
              location.pathname === menu.path ? "active-icon" : "inactive-icon"
            }
          >
            <img
              className="bottom-bar-image"
              src={menu.icon}
              alt="menu"
            />
          </span>
          {/* <span>{menu.name}</span> */}
        </button>
      ))}
    </div>
  );
};

export default BottomBar;
