export interface CategoryItem {
  categoryId: number;
  name: string;
  color: string;
  isDefault: string;
}

export interface ChallengeState {
  targetAmount: number;
  leftoverAmount: number;
  challengeId: number;
  challengeStatus: string;
}

export interface SpentItem {
  category: string | null;
  color: string | null;
  content: string | null;
  payAmount: string | number | null;
}

export interface ChallengeItem {
  category: string;
  //color: string;
  content: string;
  pay_amount: number;
}

export interface User {
  nickname: string;
  profileImage: string;
  leftoverAmount: number;
  latestTime: String;
}