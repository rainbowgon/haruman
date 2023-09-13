import { useEffect, useState } from "react";
import "../styles/NotFoundPageStyle.scss"

const NotFoundPage = () => {
  const [errorLog, setErrorLog] = useState("404");
  
  useEffect(() => {
    
  }, [])

  return (
    <>
      <div className="notfount">
        <h1 className="notfound_title">{errorLog} ERROR</h1>
        <p className="notfound_text">이 페이지는 아직 공사중이에요!</p>
      </div>
    </>
  );
};

export default NotFoundPage;
