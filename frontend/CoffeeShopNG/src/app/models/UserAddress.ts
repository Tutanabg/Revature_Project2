import { User } from "./User";


export class UserAddress {

    addressID: number;
    user: User;
    street: string;
    city: string;
    zipCode: string;


    constructor(zipCode?: string, city?: string, street?: string, user?: User, addressID?: number){
      this.addressID = addressID;
      this.user = user;
      this.street = street;
      this.city = city;
      this.zipCode = zipCode;
  }
    
}