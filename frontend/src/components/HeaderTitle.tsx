import "../styles/components/HeaderStyle.scss";

interface InputProps {
  SubTitle: string;
  MainTitle: string;
}

export default function HeaderTitle({ SubTitle, MainTitle }: InputProps) {
  return (
    <div className="header">
      <h2 className="sub_title">{SubTitle}</h2>
      <h1 className="main_title">{MainTitle}</h1>
    </div>
  );
}
