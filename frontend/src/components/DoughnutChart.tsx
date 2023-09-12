// DonutChart.tsx
import React, { useEffect, useState } from "react";
import { PieChart, Pie, Cell } from "recharts";
import axios from "axios";

import "../styles/theme.css";

type ExpenseData = {
  name: string;
  value: number;
};

const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042"];

const DonutChart: React.FC = () => {
  const [data, setData] = useState<ExpenseData[]>([]);

  useEffect(() => {
    // 백엔드에서 데이터를 가져옵니다.
    axios
      .get("api명세서가 나오면 작성할게요!")
      .then((response) => {
        setData(response.data);
      })
      .catch((error) => {
        console.error("Error fetching expense data:", error);
      });
  }, []);

  return (
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
        outerRadius={80}
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
  );
};

export default DonutChart;
