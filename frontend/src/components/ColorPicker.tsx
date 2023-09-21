import {useState} from "react";

import Category from "./Category";

import "../styles/components/ColorPickerStyle.scss"

const ColorPicker = () => {
  
  const [colors, setColors] = useState<string[]>([
    "BLACK_01",
    "BLACK_02",
    "BLACK_03",
    "BLUE_01",
    "BLUE_02",
    "BLUE_03",
    "BROWN_01",
    "BROWN_02",
    "BROWN_03",
    "GREEN_01",
    "GREEN_02",
    "GREEN_03",
    "ORANGE_01",
    "ORANGE_02",
    "ORANGE_03",
    "PINK_01",
    "PINK_02",
    "PINK_03",
    "PURPLE_01",
    "PURPLE_02",
    "PURPLE_03",
    "RED_01",
    "RED_02",
    "RED_03",
    "YELLOW_01",
    "YELLOW_02",
    "YELLOW_03",
  ])
  
  return (
    <div className="colorpicker_style">
      {
        colors &&
        colors.map((color) =>(
          <Category
              className="categoryprops"
              color={color}
            />
        ))
      }
    </div>
  );
};

export default ColorPicker;