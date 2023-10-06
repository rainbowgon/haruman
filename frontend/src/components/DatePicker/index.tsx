import { Dispatch, SetStateAction } from "react";
import DatePicker from "react-datepicker";
import { getMonth, getYear } from "date-fns";
import "react-datepicker/dist/react-datepicker.css";

import { RiArrowLeftSLine, RiArrowRightSLine } from "react-icons/ri";
import styles from "./DatePicker.module.scss";

interface Props {
  birthDate: Date | null;
  setBirthDate: Dispatch<SetStateAction<Date | null>>;
}

const YEARS = Array.from(
  { length: getYear(new Date()) + 1 - 2000 },
  (_, i) => getYear(new Date()) - i,
);
const MONTHS = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

const BirthDatePick = ({ birthDate, setBirthDate }: Props) => {
  return (
    <div className={styles.datePickerWrapper}>
      <DatePicker
        dateFormat="yyyy.MM.dd"
        formatWeekDay={(nameOfDay) => nameOfDay.substring(0, 1)}
        showYearDropdown
        scrollableYearDropdown
        shouldCloseOnSelect
        yearDropdownItemNumber={100}
        minDate={new Date("1900-01-01")}
        maxDate={new Date()}
        selected={birthDate}
        calendarClassName={styles.calenderWrapper}
        dayClassName={(d) =>
          birthDate && d.getDate() === birthDate.getDate()
            ? styles.selectedDay
            : styles.unselectedDay
        }
        onChange={(date) => setBirthDate(date)}
        className={styles.datePicker}
        renderCustomHeader={({
          date,
          changeYear,
          decreaseMonth,
          increaseMonth,
          prevMonthButtonDisabled,
          nextMonthButtonDisabled,
        }) => (
          <div className={styles.customHeaderContainer}>
            <button
              type="button"
              onClick={decreaseMonth}
              className={styles.monthButton}
              disabled={prevMonthButtonDisabled}
            >
              <RiArrowLeftSLine fill="#05668D" />
            </button>
            <div>
              <span className={styles.month}>{MONTHS[getMonth(date)]}</span>
              <select
                value={getYear(date)}
                className={styles.year}
                onChange={({ target: { value } }) => changeYear(+value)}
              >
                {YEARS.map((option) => (
                  <option
                    key={option}
                    value={option}
                  >
                    {option}
                  </option>
                ))}
              </select>
            </div>
            <button
              type="button"
              onClick={increaseMonth}
              className={styles.monthButton}
              disabled={nextMonthButtonDisabled}
            >
              <RiArrowRightSLine fill="#05668D" />
            </button>
          </div>
        )}
      />
    </div>
  );
};

export default BirthDatePick;
