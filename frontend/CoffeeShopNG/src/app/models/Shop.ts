import { Picture } from "./Picture";

export class Shop {
    shopID: number;
    shopLocation: string;
    shopPic: Picture;


  constructor(shopID: number, shopLocation: string, shopPic: Picture) {
    this.shopID = shopID
    this.shopLocation = shopLocation
    this.shopPic = shopPic
  }

}