export class Ingredient {
    ingredientID: number;
    ingredientName: string;
    cost: number;


  constructor(ingredientID: number, ingredientName: string, cost: number) {
    this.ingredientID = ingredientID
    this.ingredientName = ingredientName
    this.cost = cost
  }

}