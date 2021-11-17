import { ItemCategory } from "./ItemCategory";
import { Picture } from "./Picture";

export class MenuItem {

    itemID: number;
    itemName: string;
    itemPrice: number;
    itemPrepTime: number;
    itemCategory: ItemCategory;
    itemPic: Picture;

  constructor(
    itemID: number, 
    itemName: string, 
    itemPrice: number, 
    itemPrepTime: number, 
    itemCategory: ItemCategory, 
    itemPic: Picture
) {
    this.itemID = itemID
    this.itemName = itemName
    this.itemPrice = itemPrice
    this.itemPrepTime = itemPrepTime
    this.itemCategory = itemCategory
    this.itemPic = itemPic
  }
	
}