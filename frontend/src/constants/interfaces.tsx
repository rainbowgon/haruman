export interface ChallengeItem {
  category: string;
  content: string;
  pay_amount: number;
}

export interface User {
  nickname: string;
  profileImage: string;
  leftoverAmount: number;
  latestTime: String;
}