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
  categoryId? : number;
  payTime? : string;
  payAmount? : number;
  content? : string;
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
