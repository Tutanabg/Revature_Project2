import { Role } from '../models/Role';

export class User {
    userID: number;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    userRole: Role = new Role("customer", 1);
    //     customer: Role = new Role("customer", 1);
    //     manager: Role = new Role("manager", 2);



    //params can have default values (optional?, we don't have to provided)
    constructor(username?: string, password?: string, phoneNumber?: string, email?: string, firstName?: string, lastName?: string, userID?: number){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}