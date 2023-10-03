import React, { useEffect, useState } from "react";
import { PieChart, Pie, Cell } from "recharts";
import axios from "axios";
import "../styles/theme.css";
import { API_URL } from "../constants/urls";

export interface ExpenseItem {
  id: number;
  challengeId: number;
  categoryName: string;
  payTime: string;
  payAmount: number;
  content: string;
}

// interface DonutChartProps {
//   onCategoriesProcessed: (data: CategoryItem[]) => void;
// }

export interface CategoryItem {
  categoryId: number;
  name: string;
  color: string;
  isDefault: string;
  content: string;
  cnt: number | null;
}

const DonutChart: React.FC = () => {
  const [categories, setCategories] = useState<CategoryItem[]>([]); // 초기값은 빈 배열
  const [message, setMessage] = useState<string>("");

  const accessToken = process.env.REACT_APP_accessToken;
  const contextPath = `/api`;
  const challengeAPI = "/challenges/24";

  useEffect(() => {
    selectDailyChallenge();
  }, []);

  const selectDailyChallenge = () => {
    axios
      .get(`${API_URL}${contextPath}${challengeAPI}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((response) => {
        const challengeData: ExpenseItem[] = response.data.data;
        console.log(response.data.data);
        const aggregatedCategories: CategoryItem[] = challengeData.reduce(
          (acc: any, expense) => {
            const existingCategory = acc.find(
              (cat: CategoryItem) => cat.name === expense.categoryName,
            );

            if (existingCategory) {
              existingCategory.cnt += expense.payAmount;
            } else {
              acc.push({
                categoryId: expense.id, // 임의로 ID를 지정했는데, 실제 ID를 어떻게 설정할지 확인 필요
                name: expense.categoryName,
                color: "BLACK_01", // 기본 색상을 지정했는데, 실제 색상 매핑 로직 필요
                isDefault: "DEFAULT",
                cnt: expense.payAmount,
              });
            }

            return acc;
          },
          [],
        );

        setCategories(aggregatedCategories);
        // onCategoriesProcessed(aggregatedCategories);
      })
      .catch((error) => {
        console.error("Error fetching challenge data:", error);
        setMessage("데이터 로딩 중 오류가 발생했습니다.");
      });
  };

  const getColorByCategory = (categoryName: string): string => {
    const category = categories.find((cat) => cat.name === categoryName);
    return category ? `var(--${category.color})` : "#8884d8"; // 기본 색상
  };

  return (
    <div>
      <PieChart
        width={400}
        height={400}
      >
        <Pie
          dataKey="cnt"
          isAnimationActive={false}
          data={categories}
          cx={200}
          cy={200}
          outerRadius={120}
          innerRadius={40}
          label
        >
          {categories.map((category, index) => (
            <Cell
              key={`cell-${index}`}
              fill={getColorByCategory(category.name)}
            />
          ))}
        </Pie>
      </PieChart>
      {/* {message && <div className="error-message">{message}</div>} */}
    </div>
  );
};

export default DonutChart;
