import DatePicker from "react-datepicker";
import { useState } from "react";
import "react-datepicker/dist/react-datepicker.css";

const Calendar = () => {
  const [birthDate, setBirthDate] = useState<Date | null>(new Date());
  const styles: any = {};
  return (
    <DatePicker
      className={styles.datePicker}
      dateFormat="yyyy.MM.dd" // 날짜 형태
      shouldCloseOnSelect // 날짜를 선택하면 datepicker가 자동으로 닫힘
      minDate={new Date("2000-01-01")} // minDate 이전 날짜 선택 불가
      maxDate={new Date()} // maxDate 이후 날짜 선택 불가
      selected={birthDate}
      onChange={(date) => setBirthDate(date)}
    />
  );
};

export default Calendar;
