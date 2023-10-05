import "../styles/components/MiddleStyle.scss";

interface InputProps {
  SubTitle: string;
  MainTitle: string;
}

export default function MiddleTitle({ SubTitle, MainTitle }: InputProps) {
  return (
    <div className="Middle">
      <h2 className="Middle_sub_title">{SubTitle}</h2>
      <h1 className="Middle_main_title">{MainTitle}</h1>
    </div>
  );
}
