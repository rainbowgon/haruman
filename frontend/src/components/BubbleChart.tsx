// BubbleChartForce.tsx
import React, { useEffect, useState, useRef } from "react";
import * as d3 from "d3";
import axios from "axios";

// style
import "../styles/bubblechart.css";
import "../styles/_util.scss";
import "../styles/theme.css";
import { API_URL } from "../constants/urls";

import { User } from "../constants/interfaces";

type BalanceData = {
  nickname: string;
  profileImage: string;
  leftoverAmount: number;
  latestTime: String;
};

interface BubbleChartForceProps {
  onBubbleClick: (range: DataPoint) => void;
}

interface DataPoint {
  min: number;
  max: number;
  users: number;
  label: string;
  x?: number;
  y?: number;
  color?: string;
}

const BubbleChartForce: React.FC<BubbleChartForceProps> = ({
  onBubbleClick,
}) => {
  const [ranges, setRanges] = useState<DataPoint[]>([
    {
      min: 0,
      max: 2000,
      users: 0,
      label: "0~2000원",
      color: "var(--brand1_main)",
    },
    {
      min: 2001,
      max: 4000,
      users: 0,
      label: "2001~4000원",
      color: "var(--brand2_main)",
    },
    {
      min: 4001,
      max: 6000,
      users: 0,
      label: "4001~6000원",
      color: "var(--brand2_sub)",
    },
    {
      min: 6001,
      max: 8000,
      users: 0,
      label: "6001~8000원",
      color: "var(--point2_main)",
    },
    {
      min: 8001,
      max: 10000,
      users: 0,
      label: "8001~10000원",
      color: "var(--brand1_sub)",
    },
  ]);

  const [data, setData] = useState<DataPoint[]>(ranges);
  const svgRef = useRef<SVGSVGElement | null>(null);

  const dummyBalances: BalanceData[] = [
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "명정루",
      leftoverAmount: 4320,
      latestTime: "2023-09-13T10:26:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "푸더가든",
      leftoverAmount: 2700,
      latestTime: "2023-09-13T10:10:10",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "다이어터",
      leftoverAmount: 1700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "지각대장",
      leftoverAmount: 6700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "남고니",
      leftoverAmount: 8700,
      latestTime: "2023-09-13T09:55:33",
    },
    {
      profileImage: "image_url_kajdskjfasdfegjalad",
      nickname: "여배우",
      leftoverAmount: 5700,
      latestTime: "2023-09-13T09:55:33",
    },
  ];

  useEffect(() => {
    const balances = dummyBalances;
    console.log("처음 들어오는 데이터", data);

    // 각 범위별로 사용자 수를 계산
    const updatedData = [...ranges];

    updatedData.map((range) => ({ ...range, users: 0 }));

    for (let balanceData of balances) {
      for (let range of updatedData) {
        if (
          balanceData.leftoverAmount >= range.min &&
          balanceData.leftoverAmount <= range.max
        ) {
          range.users++;
          break;
        }
      }
    }

    setData(updatedData);
    setRanges(updatedData);
    createForceBubbleChart(updatedData);
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
    // const accessToken = sessionStorage.getItem("accessToken");
    // const host_id = parseInt(sessionStorage.getItem("userIdx"), 10);
    // axios.get(`${API_URL}/api/challenges/people`, {
    //   headers: {
    //     Authorization: accessToken,
    //   },
    // });
    //   .then((response) => {
    //       const usersData: UserData[][] = response.data.data;

    //       // 모든 사용자 데이터를 하나의 배열로 펼치기
    //       const flattenedUsers = usersData.flat();

    //       // 각 범위별로 사용자 수를 계산
    //       const updatedData = [...ranges];
    //       for (let user of flattenedUsers) {
    //         for (let range of updatedData) {
    //           if (
    //             user.leftoverAmount >= range.min &&
    //             user.leftoverAmount <= range.max
    //           ) {
    //             range.users++;
    //             break;
    //           }
    //         }
    //       }

    //       setData(updatedData);
    //       setRanges(updatedData);
    //       createForceBubbleChart(updatedData);
    //     })
    //     .catch((error) => {
    //       console.error("Error fetching user data:", error);
    //     });
    // }, []);
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
  }, []);

  const createForceBubbleChart = (data: typeof ranges) => {
    console.log("버블차트 생성시 받아오는 데이터", data);
    const margin = { top: 70, right: 100, bottom: 20, left: -30 };
    const svg = d3.select(svgRef.current);
    svg.selectAll("*").remove();

    const handleBubbleClick = (range: DataPoint) => {
      onBubbleClick(range);
      console.log("클릭되었습니다.");
    };

    const g = svg
      .append("g")
      .attr("transform", `translate(${margin.left},${margin.top})`);

    const width = 600;
    const height = 300;

    const simulation = d3
      .forceSimulation<DataPoint>(data)
      .alphaDecay(0.05)
      .alphaMin(0.1)
      .force("x", d3.forceX(width / 2).strength(0.05))
      .force("y", d3.forceY(height / 2).strength(0.05))
      .force(
        "collide",
        d3.forceCollide((d: any) => d.users + 29),
      )
      .on("tick", ticked);

    function ticked() {
      console.log("Ticked 시 받아오는 데이터", data);
      const u = g.selectAll<SVGCircleElement, DataPoint>("circle").data(data);

      u.enter()
        .append<SVGCircleElement>("circle")
        .attr("r", (d) => d.users * 3)
        .attr("fill", (d) => d.color || "#8884d8")
        .attr("stroke", "#fff")
        .attr("stroke-width", 2)
        .on("mouseover", (event, d) => {
          const tooltip = d3
            .select("body")
            .append("div")
            .classed("tooltip", true)
            .style("position", "absolute")
            .style("background-color", "white")
            .style("padding", "8px")
            .style("border", "1px solid #ccc")
            .style("border-radius", "4px")
            .text(`${d.label}: ${d.users} users`);

          const { pageX, pageY } = event;
          tooltip
            .style("left", `${pageX + 5}px`)
            .style("top", `${pageY - 30}px`);
        })
        .on("mouseout", () => {
          d3.select("body").selectAll("div.tooltip").remove();
        })
        .merge(u)
        .attr("cx", (d: any) => d.x)
        .attr("cy", (d: any) => d.y)
        .on("click", (event, d) => {
          handleBubbleClick(d);
        });

      u.exit().remove();
    }
  };

  return (
    <svg
      ref={svgRef}
      width={400}
      height={350}
    />
  );
};

export default BubbleChartForce;
