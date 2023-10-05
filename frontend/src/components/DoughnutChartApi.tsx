import React, { useEffect, useState } from "react";
import { PieChart, Pie, Cell, Legend } from "recharts";
import axios from "axios";
import "../styles/theme.css";
import { API_URL } from "../constants/urls";

// styles
import "../styles/components/CategoryStyle.scss";

export interface ExpenseItem {
  id: number;
  challengeId: number;
  categoryName: string;
  payTime: string;
  payAmount: number;
  content: string;
  categoryColor: string;
}

// interface DonutChartProps {
//   onCategoriesProcessed: (data: CategoryItem[]) => void;
// }

export interface CategoryItem {
  categoryId: number;
  name: string;
  categoryColor: string;
  isDefault: string;
  content: string;
  cnt: number | null;
}

const DonutChart: React.FC = () => {
  const [categories, setCategories] = useState<CategoryItem[]>([]);
  const [message, setMessage] = useState<string>("");

  // 테스트용
  // const accessToken = process.env.REACT_APP_accessToken;
  // 배포용
  const accessToken = sessionStorage.getItem("accessToken");
  const contextPath = `/api`;
  const challengeAPI = "/challenges/23";

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
      ///////////////////////////// 금액 별로 차트를 그릴 경우///////////////////////////////////////////
      // .then((response) => {
      //   const challengeData: ExpenseItem[] = response.data.data;
      //   const aggregatedCategories: CategoryItem[] = challengeData.reduce(
      //     (acc: any, item) => {
      //       const existingCategory = acc.find(
      //         (cat: CategoryItem) => cat.name === item.categoryName,
      //       );

      //       if (existingCategory) {
      //         existingCategory.cnt += item.payAmount;
      //       } else {
      //         acc.push({
      //           categoryId: item.id,
      //           name: item.categoryName,
      //           categoryColor: item.categoryColor,
      //           isDefault: "DEFAULT",
      //           content: item.content,
      //           cnt: item.payAmount,
      //         });
      //       }

      //       return acc;
      //     },
      //     [],
      //   );

      //   setCategories(aggregatedCategories);
      // })
      //////////////////////////////////////////////////////////////////////////////////////////////////
      /////////////////////////////////////////////카테고리별로 차트를 그릴 경우////////////////////////////////////
      .then((response) => {
        const challengeData: ExpenseItem[] = response.data.data;
        const aggregatedCategories: CategoryItem[] = challengeData.reduce(
          (acc: any, item) => {
            const existingCategory = acc.find(
              (cat: CategoryItem) => cat.name === item.categoryName,
            );

            if (existingCategory) {
              existingCategory.cnt += 1;
            } else {
              acc.push({
                categoryId: item.id,
                name: item.categoryName,
                categoryColor: item.categoryColor,
                isDefault: "DEFAULT",
                content: item.content,
                cnt: 1,
              });
            }

            return acc;
          },
          [],
        );

        setCategories(aggregatedCategories);
      })
      /////////////////////////////////////////////////////////////////////////////////////////////////////
      .catch((error) => {
        console.error("Error fetching challenge data:", error);
        setMessage("데이터 로딩 중 오류가 발생했습니다.");
      });
  };

  const getColorByCategory = (categoryColor: string): string => {
    return `var(--${categoryColor})`;
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
              fill={getColorByCategory(category.categoryColor)}
            />
          ))}
        </Pie>
        <Legend />
      </PieChart>
      {/* {message && <div className="error-message">{message}</div>} */}
    </div>
  );
};

export default DonutChart;
