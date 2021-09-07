export class Drug{
  id: number;
  name: string;
  recommendedAmount: number;

  constructor(id: number, name: string, recommendedAmount: number) {
    this.id = id;
    this.name = name;
    this.recommendedAmount = recommendedAmount;
  }
}
