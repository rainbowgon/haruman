export interface CategoryItem {
  categoryId: number;
  category: string;
  color: string;
  isDefault: string;
}

export interface ChallengeState {
  id: number;
  nickname: string;
  startTime: string;
  endTime: string;
  challengeStatus: string;
  targetAmount: number;
  usedAmount: number;
  leftoverAmount: number;
  isViewed: String;
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
  usedAmount: number;
  latestTime: String;
}
