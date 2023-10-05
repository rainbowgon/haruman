// BubbleChartForce.tsx
import React, { useEffect, useState, useRef } from "react";
import * as d3 from "d3";
import axios from "axios";

// style
import "../styles/bubblechart.css";
import "../styles/_util.scss";
import "../styles/theme.css";
import { API_URL } from "../constants/urls";

import { UserData, GroupedData, ApiResponse } from "../constants/interfaces";

// ----- BubbleChartStyle(START)
import "../styles/ranking/BubbleChartStyle.scss";

interface BubbleChartLabel {
  color: string;
  value: string;
}

function getColorForGroup(groupKey: string) {
  switch (groupKey) {
    case "챌린지 실패":
      return "var(--brand1_main)";
    case "2000원 이하":
      return "var(--GRAPH_01)";
    case "4000원 이하":
      return "var(--GRAPH_02)";
    case "6000원 이하":
      return "var(--GRAPH_03)";
    case "8000원 이하":
      return "var(--GRAPH_04)";
    case "10000원 이하":
      return "var(--GRAPH_05)";
    case "0원":
      return "var(--point2_op_60)";
    default:
      return "#8884d8";
  }
}

const BubbleChartLabels: BubbleChartLabel[] = [
  {
    color: "GRAPH_07",
    value: "무지출챌린지",
  },
  {
    color: "GRAPH_01",
    value: "2000원 이하",
  },
  {
    color: "GRAPH_02",
    value: "4000원 이하",
  },
  {
    color: "GRAPH_03",
    value: "6000원 이하",
  },
  {
    color: "GRAPH_04",
    value: "8000원 이하",
  },
  {
    color: "GRAPH_05",
    value: "10000원 미만",
  },
  {
    color: "GRAPH_06",
    value: "챌린지 실패",
  },
];
// ----- BubbleChartStyle(END)

type BalanceData = {
  nickname: string;
  profileImage: string;
  usedAmount: number;
  latestTime: String;
};

interface BubbleChartForceProps {
  onBubbleClick: (range: DataPoint) => void;
}

export interface DataPoint {
  min: number;
  max: number;
  users: number;
  label: string;
  x?: number;
  y?: number;
  color?: string | null;
}

// ----- BubbleChartForce(START)
const BubbleChartForce: React.FC<BubbleChartForceProps> = ({
  onBubbleClick,
}) => {
  const [ranges, setRanges] = useState<DataPoint[]>([]);
  // ----- BubbleChartForce(END)

  const [data, setData] = useState<DataPoint[]>(ranges);
  const svgRef = useRef<SVGSVGElement | null>(null);
  const [pageInfo, setPageInfo] = useState<any>({});
  useEffect(() => {
    const fetchChartData = async () => {
      // 테스트용
      // const accessToken = process.env.REACT_APP_accessToken;
      // 배포용
      const accessToken = localStorage.getItem("accessToken");
      try {
        const response = await axios.get<ApiResponse>(
          `${API_URL}/api/challenges/people`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          },
        );

        const updatedData: DataPoint[] = response.data.data.map((group) => ({
          label: group.groupKey,
          users: group.userList.length,
          color: getColorForGroup(group.groupKey),
          min: 0,
          max: 100000,
        }));

        setPageInfo(response.data.pageInfo);
        setData(updatedData);
        setRanges(updatedData);
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    };

    fetchChartData();
  }, []);

  useEffect(() => {
    if (pageInfo.size) {
      createForceBubbleChart(ranges, pageInfo);
    }
  }, [ranges, pageInfo]);
  const updatedData: DataPoint[] = [...ranges].map((range) => ({
    ...range,
  }));

  const createForceBubbleChart = (data: typeof ranges, pageInfo: any) => {
    const allUsers = pageInfo.size;
    const margin = { top: 70, right: 100, bottom: 20, left: -30 };
    const svg = d3.select(svgRef.current);
    svg.selectAll("*").remove();

    const handleBubbleClick = (range: DataPoint) => {
      onBubbleClick(range);
    };

    const width = 400;
    const height = 500;
    const g = svg
      .append("g")
      .attr(
        "transform",
        `translate(${width / window.innerWidth},${height / window.innerWidth})`,
      );

    const simulation = d3
      .forceSimulation<DataPoint>(data)
      .alphaDecay(0.05)
      .alphaMin(0.1)
      .force("x", d3.forceX(width / 2).strength(0.05))
      .force("y", d3.forceY(height / 2).strength(0.05))
      .force(
        "collide",
        d3.forceCollide(
          (d: any) => (d.users / allUsers) * (window.innerWidth * 0.3),
        ),
      )
      .on("tick", ticked);

    function ticked() {
      const u = g.selectAll<SVGCircleElement, DataPoint>("circle").data(data);

      u.enter()
        .append<SVGCircleElement>("circle")
        .attr("r", (d) => (d.users / allUsers) * (window.innerWidth * 0.3))
        .attr("fill", (d) => d.color || "#8884d8")
        // .attr("stroke", "#fff")
        // .attr("stroke-width", 2)
        .on("mouseover", (event, d) => {
          const tooltip = d3.select("body").append("div");
          //   .classed("tooltip", true)
          //   .style("position", "absolute")
          //   .style("background-color", "white")
          //   .style("padding", "8px")
          //   .style("border", "1px solid #ccc")
          //   .style("border-radius", "4px");
          // .text(`${d.label}: ${d.users} users`);

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
    <div className="bubblechart">
      <div className="bubblechart_labels">
        {BubbleChartLabels.map((lable) => (
          <div className="bubblechart_label">
            <div className={`bubblechart_color_pointer ${lable.color}`} />
            <p className={`bubblechart_value`}>{lable.value}</p>
          </div>
        ))}
      </div>
      <svg
        ref={svgRef}
        width={400}
        height={350}
      />
    </div>
  );
};

export default BubbleChartForce;
