export interface CategoryItem {
  categoryId: number;
  name: string;
  color: string;
  isDefault: string;
  cnt : number | null;
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
  categoryId : number | null;
  payTime : string | null;
  payAmount : number | null;
  content : string | null;
}
// "id": 1,
// "challengeId": 1,
// "categoryName": "기타",
// "payTime": "2023-09-27T15:38:19.68047",
// "payAmount": 8000,
// "content": "참치김밥"
 
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
