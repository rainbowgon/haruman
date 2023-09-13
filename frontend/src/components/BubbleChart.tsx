// BubbleChartForce.tsx
import React, { useEffect, useState, useRef } from "react";
import * as d3 from "d3";
import axios from "axios";

// style
import "../styles/bubblechart.css";

type BalanceData = {
  balance: number;
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
}

const ranges = [
  { min: 0, max: 2000, users: 0, label: "0~2000원" },
  { min: 2001, max: 4000, users: 0, label: "2001~4000원" },
  { min: 4001, max: 6000, users: 0, label: "4001~6000원" },
  { min: 6001, max: 8000, users: 0, label: "6001~8000원" },
  { min: 8001, max: 10000, users: 0, label: "8001~10000원" },
];

const BubbleChartForce: React.FC<BubbleChartForceProps> = ({
  onBubbleClick,
}) => {
  const [data, setData] = useState<DataPoint[]>(ranges);
  const svgRef = useRef<SVGSVGElement | null>(null);

  const dummyBalances: BalanceData[] = [
    { balance: 1500 },
    { balance: 2300 },
    { balance: 5000 },
    { balance: 7500 },
    { balance: 9000 },
    { balance: 1200 },
    { balance: 2400 },
    { balance: 5200 },
    { balance: 5400 },
    { balance: 2300 },
    { balance: 2300 },
    { balance: 2300 },
    { balance: 2300 },
    { balance: 1200 },
    { balance: 1200 },
    { balance: 1200 },
    { balance: 1200 },
    { balance: 1200 },
    { balance: 1200 },
    { balance: 1200 },
    { balance: 1200 },
    { balance: 1200 },
    { balance: 4400 },
    { balance: 8900 },
    { balance: 7500 },
    { balance: 4600 },
    { balance: 5400 },
    { balance: 7700 },
    { balance: 4400 },
    { balance: 1100 },
    { balance: 2200 },
    { balance: 3300 },
    { balance: 4400 },
    { balance: 5500 },
    { balance: 6600 },
    { balance: 7700 },
    { balance: 7700 },
    { balance: 8800 },
    { balance: 8800 },
    { balance: 8800 },
    { balance: 8800 },
    { balance: 8800 },
    { balance: 8800 },
    { balance: 8800 },
    { balance: 9900 },
  ];

  useEffect(() => {
    const balances = dummyBalances;
    console.log("처음 들어오는 데이터", data);

    // 각 범위별로 사용자 수를 계산
    const updatedData = [...ranges];
    for (let balanceData of balances) {
      for (let range of updatedData) {
        if (
          balanceData.balance >= range.min &&
          balanceData.balance <= range.max
        ) {
          range.users++;
          break;
        }
      }
    }

    setData(updatedData);
    createForceBubbleChart(updatedData);
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
    // axios
    //   .get("/api/users/balances")
    //   .then((response) => {
    //     const balances: BalanceData[] = response.data;
    //     // 각 범위별로 사용자 수를 계산
    //     const updatedData = [...ranges];
    //     for (let balanceData of balances) {
    //       for (let range of updatedData) {
    //         if (
    //           balanceData.balance >= range.min &&
    //           balanceData.balance <= range.max
    //         ) {
    //           range.users++;
    //           break;
    //         }
    //       }
    //     }
    //     setData(updatedData);
    //     createForceBubbleChart(updatedData);
    //   })
    //   .catch((error) => {
    //     console.error("Error fetching balance data:", error);
    //   });
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////api 통신 후 사용할 부분!!//////////////////////////////////////////////////////////////////
  }, []);

  const createForceBubbleChart = (data: typeof ranges) => {
    console.log("버블차트 생성시 받아오는 데이터", data);
    const margin = { top: 70, right: 30, bottom: 20, left: -30 };
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
        .attr("r", (d) => d.users * 6)
        .attr("fill", "#8884d8")
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
