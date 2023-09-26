// DonutChart.tsx
import React, { useEffect, useState } from "react";
import { PieChart, Pie, Cell } from "recharts";
import axios from "axios";
import { ChallengeItem } from "../constants/interfaces";

import "../styles/theme.css";

type ExpenseData = {
  name: string;
  value: number;
};

// api용
// type DonutChartProps = {
//   datas: ExpenseData[];
// };

// 더미데이터용
type DonutChartProps = {
  datas: ChallengeItem[];
};

const aggregateByCategory = (items: ChallengeItem[]): ExpenseData[] => {
  const result: Record<string, number> = {};

  items.forEach((item) => {
    if (!result[item.category]) {
      result[item.category] = 0;
    }
    result[item.category] += item.pay_amount;
  });

  return Object.entries(result).map(([name, value]) => ({ name, value }));
};

const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042"];

const DonutChart: React.FC<DonutChartProps> = ({ datas }) => {
  const [data, setData] = useState<ExpenseData[]>([]);

  useEffect(() => {
    // 더미용
    const aggregatedData = aggregateByCategory(datas);
    setData(aggregatedData);
  }, [datas]);

  // api용
  //   axios
  //     .get(`api/challenges/{date}`)
  //     .then((response) => {
  //       const aggregatedData = aggregateByCategory(response.data);
  //       setData(aggregatedData);
  //     })
  //     .catch((error) => {
  //       console.error("Error fetching expense data:", error);
  //     });
  // }, []);

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
  );
};

export default DonutChart;
