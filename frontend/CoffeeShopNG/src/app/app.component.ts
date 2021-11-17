import { Component } from '@angular/core';
import { User } from './models/User';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Coffee Shop App';
  coffeeShopName = 'Coffee Shop'
  user: User;
  constructor(private loginServ: LoginService){

  }

  isLoggedin(){
    if(this.user!=null){
      return true;
    }
    return false;
  }

  getUser(){
    return this.loginServ.currentUser;
  }

  getUsername(){
    return (this.getUser) ? this.getUser().username : "";
  }

  logout() {
    this.loginServ.logout();
    console.log(this.loginServ.currentUser);
    this.user = null;
  }

  isManager(): boolean{
    let user = this.getUser();
    //console.log(user);
    if(this.getUser() && user.userRole.roleName == "manager"){
      return true;
    }
    return false;
  }
}
