import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import '../../styles/calendar/CalenderForm.scss'
import moment from "moment";
import axios from "axios";
import {useSelector} from "react-redux";

const CalendarForm = () => {
// const attend = useSelector((state) => state.userState.user?.accessList)
// const userId = useSelector((state) => state.userState.user?.userId)
const [finishedProject, setFinishedProject] = useState('')
const [countMemory, setCountMemory] = useState(0)

const [attendanceDates, setAttendanceDates] = useState([
"2023-09-01",
"2023-09-04",
"2023-09-07",
"2023-09-08",
"2023-09-12",
"2023-09-15",
"2023-09-20",
])

// useEffect(() => {
// const myAttendance = attend?.map(date => date.slice(0, 10)) || attendanceDates
// setAttendanceDates(myAttendance)
// },[]);

// useEffect(() => {
// const countCapsuleURL = 'https://i9a608.p.ssafy.io:8000/project/myinfo/info'

// try {
// axios.get(`${countCapsuleURL}`, {
// headers: {
// 'userId': userId,
// }
// })
// .then((response) => {
// const finishedCapsules = response.data.doneNum || 0
// const counts = response.data.articleNum
// setFinishedProject(finishedCapsules)
// setCountMemory(counts)
// })
// .catch(() => {
// const finishedCapsules = 0
// setFinishedProject(finishedCapsules)
// })
// }
// catch (error) {
// }
// },[]);

const [value, onChange] = useState(new Date())
// const user = useSelector((state) => state.userState.user)
// const username = user?.nickname || '김싸피'
const memorys = countMemory
const countProject = finishedProject

return (
<>
  <div className="mypage_calenders">
  <Calendar
    locale="en"
    value={value}
    //   onChange:String ={onChange}
    onClickDay={(value) => alert('Clicked day: ' + value)}
    tileClassName={({ date }) => {
      // attendanceDates 배열에 있는 날짜와 현재 날짜(date)를 비교하여 클래스를 추가합니다.
      const dateString = moment(date).format("YYYY-MM-DD");
      if (attendanceDates.includes(dateString)) {
        return "highlight2"; // 스타일을 지정할 클래스 이름
      }
    }}
  />
  </div>
</>
)
}

export default CalendarForm;