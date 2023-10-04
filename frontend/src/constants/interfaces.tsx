export interface CategoryItem {
  categoryId: number;
  name: string;
  color: string;
  isDefault: string;
  cnt: number | null;
}

export interface ChallengeState {
  challengeId: number;
  participantCount: number;
  targetAmount: number;
  usedAmount: number;
  leftoverAmount: number;
  challengeStatus: string;
}

export interface SpentItem {
  categoryId: number | null;
  payTime: string | null;
  payAmount: number | null;
  content: string | null;
}

export interface ChallengeItem {
  id : number;
  challengeId : number;
  categoryColor? : string;
  categoryName : string;
  payTime : string;
  payAmount : number;
  content : string;

}

export interface User {
  nickname: string;
  profileImage: string;
  usedAmount: number;
  latestTime: String;
}

export interface ChallengeDate {
  date: string;
  challengeId: number;
  status: string;
}