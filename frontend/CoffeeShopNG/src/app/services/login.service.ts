import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { UserAddress } from '../models/UserAddress';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor() { }

  currentUser: User;
  currentUserAddress: UserAddress;

  login(userAddress: UserAddress){
      this.currentUser = userAddress.user;
      this.currentUserAddress = userAddress;
  }

  logout(){
    this.currentUser = null;
    this.currentUserAddress = null;
  }
}
