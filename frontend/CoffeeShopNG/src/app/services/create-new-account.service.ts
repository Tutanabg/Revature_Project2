import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { User } from '../models/User';
import { UserAddress } from '../models/UserAddress';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateNewAccountService {

  constructor(private http: HttpClient) { }

  private postHeaders = new HttpHeaders({'Context-Type': 'application/json'});

  getAllUsernames(): Observable<Array<string>> {
    return this.http.get<Array<string>>('http://localhost:8080/users');
  }
  
  addUser(account: User): Observable<any> {
    return this.http.post('http://localhost:8080/users', account, { headers: this.postHeaders, observe: 'response'});
  }

  updateUser(account: User): Observable<any> {
    return this.http.put('http://localhost:8080/users', account, { headers: this.postHeaders, observe: 'response'});
  }

  addUserAddress(address: UserAddress): Observable<any> {
    return this.http.post('http://localhost:8080/userAddresses', address, { headers: this.postHeaders, observe: 'response'});
  }

}
