// DonutChart.tsx
import React, { useEffect, useState } from "react";
import { PieChart, Pie, Cell } from "recharts";
import axios from "axios";

// style
import "../styles/theme.css";
import { API_URL } from "../constants/urls";

interface ApiResponse {
  success: boolean;
  message: string;
  data?: ChallengeData | null;
}

interface ChallengeData {
  participantCount: number;
  leftoverAmount: number;
  challengeStatus: string | null;
  expenseList: ExpenseItem[];
}

interface ExpenseItem {
  id: number;
  challengeId: number;
  categoryName: string;
  payTime: string;
  payAmount: number;
  content: string;
}

interface ExpenseData {
  name: string;
  value: number;
}

const DonutChart: React.FC = () => {
  const [data, setData] = useState<ExpenseData[]>([]);
  const [message, setMessage] = useState<string>("");

  const selectDailyChallenge = () => {
    const accessToken = sessionStorage.getItem("accessToken");
    // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);
    const date = new Date();
    axios
      .get(`${API_URL}/api/challenges`, {
        headers: {
          Authorization: accessToken,
        },
      })
      .then((response) => {
        const apiResponse: ApiResponse = response.data;

        if (apiResponse.success && apiResponse.data) {
          const aggregatedData = apiResponse.data.expenseList.reduce(
            (acc: ExpenseData[], item: ExpenseItem) => {
              const existing = acc.find((d) => d.name === item.categoryName);
              if (existing) {
                existing.value += item.payAmount;
              } else {
                acc.push({ name: item.categoryName, value: item.payAmount });
              }
              return acc;
            },
            [],
          );
          setData(aggregatedData);
        } else {
          setMessage(apiResponse.message);
        }
      })
      .catch((error) => {
        console.error("Error fetching challenge data:", error);
        setMessage("데이터 로딩 중 오류가 발생했습니다.");
      });
  };

  useEffect(selectDailyChallenge, []);

  const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042"];

  return (
    <div>
      <PieChart
        width={400}
        height={400}
      >
        <Pie
          dataKey="value"
          isAnimationActive={false}
          data={data}
          cx={200}
          cy={200}
          outerRadius={150}
          innerRadius={70}
          fill="#8884d8"
          label
        >
          {data.map((entry, index) => (
            <Cell
              key={`cell-${index}`}
              fill={COLORS[index % COLORS.length]}
            />
          ))}
        </Pie>
      </PieChart>
      {message && <div className="error-message">{message}</div>}
    </div>
  );
};

export default DonutChart;
